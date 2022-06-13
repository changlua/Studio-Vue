package com.changlu.service.impl;

import com.changlu.common.config.ZfConfig;
import com.changlu.common.domain.ResponseResult;
import com.changlu.common.exception.ServiceException;
import com.changlu.mapper.ZfCcieMapper;
import com.changlu.mapper.ZfMUserMapper;
import com.changlu.mapper.ZfThinkMapper;
import com.changlu.security.service.TokenService;
import com.changlu.security.util.SecurityUtils;
import com.changlu.service.ISysUserService;
import com.changlu.service.ZfManageUserService;
import com.changlu.service.ZfRaceService;
import com.changlu.system.mapper.SysUserMapper;
import com.changlu.system.mapper.SysUserRoleMapper;
import com.changlu.system.pojo.SysRole;
import com.changlu.system.pojo.SysUser;
import com.changlu.system.pojo.SysUserRole;
import com.changlu.vo.manage.MUserVo;
import com.changlu.enums.UserStatusEnum;
import com.changlu.enums.ZfRoleEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @ClassName ZfManageUserServiceImpl
 * @Author ChangLu
 * @Date 4/5/2022 5:55 PM
 * @Description 管理用户业务层实现类
 */
@Service
public class ZfManageUserServiceImpl implements ZfManageUserService {

    @Resource
    private ZfMUserMapper userMapper;

    @Resource
    private SysUserMapper sysUserMapper;

    @Autowired
    private ISysUserService sysUserService;

    @Autowired
    private TokenService tokenService;

    @Resource
    private SysUserRoleMapper sysUserRoleMapper;

    @Autowired
    private ZfConfig zfConfig;

    /**
     * 查询用户接口
     */
    @Override
    public List<MUserVo> selectSysUserList(MUserVo userVo) {
        List<MUserVo> list = userMapper.selectSysUserList(userVo);
        //1、将负责人取出来放置到第一个位置（负责人在第一位）
        ArrayList<MUserVo> userList = (ArrayList<MUserVo>)list;
        for (int i = 0; i < userList.size(); i++) {
            MUserVo curUserVo = userList.get(i);
            if (curUserVo.getRoleName().contains(ZfRoleEnum.ROLE_MANAGE.getName())) {
                MUserVo temp = curUserVo;
                list.set(i, list.get(0));
                list.set(0, temp);
                break;
            }
        }
        //2、对年级进行处理，非空的补上"级"，如2019 => 2019级 (已在数据库做处理)
//        userList.stream().forEach((user) -> {
//            String gradeNum = user.getGradeNum();
//            if (ObjectUtils.isEmpty(gradeNum)) {
//                user.setGradeNum("");
//            }else{
//                user.setGradeNum(user.getGradeNum() + "级");
//            }
//        });
        return userList;
    }

    @Override
    public MUserVo selectSysUserByUserId(Long userId) {
        return userMapper.selectSysUserByUserId(userId);
    }

    @Override
    public int updateSysUser(MUserVo userVo) {
        //仅仅只更新用户的真实姓名、性别、年级、专业
        SysUser sysUser = new SysUser();
//        BeanUtils.copyProperties(userVo, sysUser); //不考虑用这种，万一别人直接通过该接口来进行伪造其他数据传进来
        sysUser.setUserId(userVo.getUserId());
        sysUser.setSex(userVo.getSex());
        sysUser.setRealName(userVo.getRealName());
        sysUser.setGradeId(userVo.getGradeId());
        sysUser.setMajorId(userVo.getMajorId());
        return sysUserService.updateSysUser(sysUser, false);
    }

    @Override
    public boolean cancelledSysUser(Long userId) {
        if (!ObjectUtils.isEmpty(userId) ){
            //1、注销账号
            if (userMapper.updateStatusByUserId(userId, UserStatusEnum.DISABLED.value()) > 0) {
                //2、删除该账号的token（由于目前使用uuid作为标识根据userId取消不了）
                //  tokenService.deleteToken(userId);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean activeSysUser(Long userId) {
        return userMapper.updateStatusByUserId(userId, UserStatusEnum.ACTIVE.value()) > 0;
    }

    /**
     * 转让负责人：由于管理员的角色权限是在成员的基础上添加的，所以我们只需要将用户的管理员角色记录删除即可
     * @param targetUserId
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean transfer(Long targetUserId) {
        //check是否当前账号为负责人
        if (!checkUserIsManage()) {
            throw new ServiceException("当前账号非管理员身份,转让失败！");
        }
        //1、修改目标对象的角色身份为负责人（新增一条用户_角色记录、新增一条当前负责人身份信息也就是历届负责人）
        List<SysUserRole> userRoles = Arrays.asList(
                new SysUserRole(targetUserId, ZfRoleEnum.ROLE_MANAGE.value()),   //目标方
                new SysUserRole(SecurityUtils.getUserId(), ZfRoleEnum.ROLE_PAST_MANAGE.value())  //账号自己
        );
        if (sysUserRoleMapper.batchUserRole(userRoles) > 0) {
            //2、删除当前负责人的角色身份（删除用户_角色记录）
            SysUserRole curUserRole = new SysUserRole(SecurityUtils.getUserId(), ZfRoleEnum.ROLE_MANAGE.value());
            if (sysUserRoleMapper.deleteUserRoleInfo(curUserRole) > 0) {
                //3、更新当前账号的身份信息
                SysUser sysUser = new SysUser();
                sysUserService.updateSysUser(sysUser, true);
                return true;
            }
        }
        throw new ServiceException("转让失败！");
    }

    /**
     * 检查当前用户与对方的用户是否为同一个年级
     * @param targetUserId 被转让者的用户id
     * @return yes return true or return false
     */
    private boolean checkUserIsSameGrade(Long targetUserId) {
        Long curUserGradeId = SecurityUtils.getUser().getGradeId();
        Long targetUserGradeId = null;
        SysUser sysUser = sysUserMapper.selectUserById(targetUserId);
        if (sysUser == null) {
            targetUserGradeId = -1L;
        }else {
            targetUserGradeId = sysUser.getGradeId();
        }
        return curUserGradeId != null && curUserGradeId.equals(targetUserGradeId);
    }

    /**
     * 检查当前用户是否为管理员
     * @return yes return true or return false
     */
    public boolean checkUserIsManage() {
        for (SysRole role : SecurityUtils.getUser().getRoles()) {
            if (role.getRoleId() != null && role.getRoleId().equals(ZfRoleEnum.ROLE_MANAGE.value())) {
                return true;
            }
        }
        return false;
    }

    /**
     * 重置密码为yml中的zf.resetPassword内容
     * @param targetUserIds
     * @return
     */
    @Override
    public boolean resetPwd(Long[] targetUserIds) {
        //重置指定用户的账号为
        String password = "{bcrypt}" + SecurityUtils.bCryptPasswordEncoder.encode(zfConfig.getResetPassword());
        return userMapper.resetPwdByUserId(targetUserIds,password) > 0;
    }


    @Resource
    private ZfCcieMapper zfCcieMapper;

    @Resource
    private ZfThinkMapper zfThinkMapper;

    @Autowired
    private ZfRaceService zfRaceService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean delete(Long targetUserId) {
        //1、删除用户账号记录、用户角色记录
        if (sysUserMapper.deleteSysUserByUserId(targetUserId) > 0 &&
            sysUserRoleMapper.deleteUserRoleByUserId(targetUserId) > 0){
            //2、删除其他关联表记录（ccie证书、race竞赛及resources资源表、think心得）
            zfCcieMapper.deleteZfCcieByUserId(targetUserId);
            zfRaceService.deleteZfRaceModelByUserId(targetUserId);
            zfThinkMapper.deleteZfThinkModelByUserId(targetUserId);
            return true;
        }
        return false;
    }




}
