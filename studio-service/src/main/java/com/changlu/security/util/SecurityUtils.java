package com.changlu.security.util;

import com.changlu.domain.LoginUser;
import com.changlu.system.pojo.SysUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @ClassName SecurityUtils
 * @Author ChangLu
 * @Date 3/28/2022 4:13 PM
 * @Description spring security工具类
 */
public class SecurityUtils {

    /**
     * BCrypt编码器
     */
    public static BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    /**
     * 获取登录用户对象
     * @return 登录用户
     */
    public static LoginUser getLoginUser(){
        return (LoginUser) getAuthentication().getPrincipal();
    }

    /**
     * 获取用户对象信息
     * @return 用户对象
     */
    public static SysUser getUser(){
        return getLoginUser().getUser();
    }

    /**
     * 获取Authentication
     */
    public static Authentication getAuthentication()
    {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    /**
     * 是否为管理员
     *
     * @param userId 用户ID
     * @return 结果
     */
    public static boolean isAdmin(Long userId)
    {
        return userId != null && 1L == userId;
    }

    /**
     * 用户ID
     **/
    public static Long getUserId()
    {
        try
        {
            return getLoginUser().getUser().getUserId();
        }
        catch (Exception e)
        {
            throw new RuntimeException("获取用户ID异常");
        }
    }

}
