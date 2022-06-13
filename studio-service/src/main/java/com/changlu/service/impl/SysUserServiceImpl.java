package com.changlu.service.impl;

import com.changlu.domain.LoginUser;
//需要web的TokenService
import com.changlu.security.service.TokenService;
import com.changlu.security.util.SecurityUtils;
import com.changlu.service.ISysUserService;
import com.changlu.system.mapper.SysMenuMapper;
import com.changlu.system.mapper.SysUserMapper;
import com.changlu.system.pojo.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @ClassName SysUserServiceImpl
 * @Author ChangLu
 * @Date 4/2/2022 7:23 PM
 * @Description 用户业务层
 */
@Service
public class SysUserServiceImpl implements ISysUserService {

    @Resource
    private SysUserMapper sysUserMapper;

    @Autowired
    private TokenService tokenService;

    @Resource
    private SysMenuMapper sysMenuMapper;

    /**
     * 更新系统用户
     * @param sysUser 系统用户更新的信息
     * @param updateOwn 是否是更新自己
     * @return
     */
    @Override
    public int updateSysUser(SysUser sysUser, Boolean updateOwn) {
        //设置当前更新用户的id、更新时间
        if (ObjectUtils.isEmpty(sysUser.getUserId())) {
            sysUser.setUserId(SecurityUtils.getUserId());
        }
        sysUser.setUpdateTime(new Date());
        //1、更新数据库用户信息
        int result = sysUserMapper.updateSysUser(sysUser);
        //2、更新redis用户信息
        if (result > 0){
            //若是更新用户本身则更新redis、非用户本身不做更新（指的是管理员对某个用户更新信息）
            if (updateOwn) {
                //走数据库，查询一遍最新的用户信息、权限不做查询依旧使用原来的（因为管理员再进行对账号授权时，不会修改对应的缓存）
                SysUser user = sysUserMapper.loadUserByUsername(SecurityUtils.getUser().getUserName());
//                List<String> perms = new ArrayList<>();;
//                if (user.isAdmin()){
//                    perms.add("*:*:*");
//                }else{
//                    perms = sysMenuMapper.selectPermsByUserId(user.getUserId());
//                }
                LoginUser newLoginUser = new LoginUser(user, SecurityUtils.getLoginUser().getPermissions());
                newLoginUser.setToken(SecurityUtils.getLoginUser().getToken());//token(uuid)依旧是当前用户自身的
                tokenService.refreshToken(newLoginUser);
            }
        }
        return result;
    }

    @Override
    public List<SysUser> selectAllocatedList(SysUser user) {
        return sysUserMapper.selectAllocatedList(user);
    }

    /**
     * 根据条件分页查询未分配用户角色列表
     *
     * @param user 用户信息
     * @return 用户信息集合信息
     */
    @Override
    public List<SysUser> selectUnallocatedList(SysUser user)
    {
        return sysUserMapper.selectUnallocatedList(user);
    }

}
