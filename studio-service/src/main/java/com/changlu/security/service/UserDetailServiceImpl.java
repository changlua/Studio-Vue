package com.changlu.security.service;

import com.changlu.domain.LoginUser;
import com.changlu.common.exception.ServiceException;
import com.changlu.system.mapper.SysMenuMapper;
import com.changlu.system.mapper.SysUserMapper;
import com.changlu.system.pojo.SysUser;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName UserDetailServiceImpl
 * @Author ChangLu
 * @Date 3/25/2022 2:01 PM
 * @Description 自定义认证数据源
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Resource
    private SysUserMapper sysUserMapper;

    @Resource
    private SysMenuMapper sysMenuMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //1、查询用户
        SysUser user = sysUserMapper.loadUserByUsername(username);
        //2、若是没有用户就抛出异常
        if (ObjectUtils.isEmpty(user)) {
            throw new ServiceException("用户名不存在，请重新输入！");
        }
        //3、若是查询到用户就去获取用户的权限，最后返回
        List<String> perms = new ArrayList<>();
        if (user.isAdmin()){
//            perms = sysMenuMapper.selectAdminPerms();
            perms.add("*:*:*");
        }else{
            perms = sysMenuMapper.selectPermsByUserId(user.getUserId());
        }
        //防止初始new SimpleGrantedAuthority()过程中报错
        if(ObjectUtils.isEmpty(perms)){
            perms.add("empty");
        }
        return new LoginUser(user,perms);
    }
}
