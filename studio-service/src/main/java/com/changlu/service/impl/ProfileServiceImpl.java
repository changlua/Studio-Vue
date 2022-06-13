package com.changlu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.changlu.common.domain.ResponseResult;
import com.changlu.common.exception.ServiceCallException;
import com.changlu.common.exception.ServiceException;
import com.changlu.security.util.SecurityUtils;
import com.changlu.service.IProfileService;
import com.changlu.service.ISysUserService;
import com.changlu.system.mapper.SysUserMapper;
import com.changlu.system.pojo.SysUser;
import com.changlu.utils.file.FileUpload;
import com.changlu.vo.PasswordVo;
import com.changlu.vo.ProfileVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.Executor;

/**
 * @ClassName ProfileServiceImpl
 * @Author ChangLu
 * @Date 4/2/2022 7:50 PM
 * @Description 用户业务实现层
 */
@Service
public class ProfileServiceImpl implements IProfileService {

    @Autowired
    private ISysUserService sysUserService;

    @Qualifier("fileUpload")
    @Autowired
    private FileUpload fileUploadUtil;

    @Resource(name = "taskExecutor")
    private Executor executor;

    @Resource
    private SysUserMapper sysUserMapper;

//    @Autowired
//    private AliyunOssUtil aliOssUtil;

    @Override
    public int updateProfile(ProfileVo profileVo) {
        SysUser sysUser = new SysUser();
        BeanUtils.copyProperties(profileVo, sysUser);
        return sysUserService.updateSysUser(sysUser, true);
    }

    @Override
    public ResponseResult updatePasswd(PasswordVo passwordVo) {
        //1、校验密码
        String oldPassword = passwordVo.getOldPassword().trim();
        String newPassword = passwordVo.getPassword().trim();
        if (ObjectUtils.isEmpty(oldPassword) || ObjectUtils.isEmpty(newPassword)){
            return ResponseResult.error("您的密码为空，重新输入！");
        }
        String userPassword = SecurityUtils.getUser().getPassword().substring(8);
        //2、校验不通过
        if (!SecurityUtils.bCryptPasswordEncoder.matches(oldPassword, userPassword)) {
            return ResponseResult.error("您的旧密码有误，请重新输入！");
        }
        //3、校验通过，重新设置密码
        passwordVo.setPassword("{bcrypt}" + SecurityUtils.bCryptPasswordEncoder.encode(newPassword));
        SysUser sysUser = new SysUser();
        BeanUtils.copyProperties(passwordVo, sysUser);
        if (sysUserService.updateSysUser(sysUser, true) > 0) {
            return ResponseResult.success("修改成功！",null);
        }
        return ResponseResult.error("修改失败！");
    }

    @Override
    public ResponseResult updateAvatar(MultipartFile file) {
        Map<String, String> fileMap = null;
        try {
            //1、获取到上传文件访问路径以及原文件名
            fileMap = fileUploadUtil.uploadFile(file);
        }catch (ServiceCallException e){
            throw new ServiceException("上传失败！",e);
        }
        String visitUrl = fileMap.get("visitUrl");
        //比较头像是否有改变
        LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<SysUser>()
                .select(SysUser::getAvatar)
                .eq(SysUser::getUserId, SecurityUtils.getUserId());
        String originAvatar = sysUserMapper.selectOne(queryWrapper).getAvatar();
        if (!ObjectUtils.isEmpty(originAvatar) && !originAvatar.equals(visitUrl)){
            //删除图片
            executor.execute(()->fileUploadUtil.deleteFile(originAvatar));
        }
        //2、更新用户
        SysUser sysUser = new SysUser();
        sysUser.setAvatar(visitUrl);
        if (sysUserService.updateSysUser(sysUser, true) > 0) {
            return ResponseResult.success("上传成功！", visitUrl);
        }
        return ResponseResult.error("上传失败！");
    }
}
