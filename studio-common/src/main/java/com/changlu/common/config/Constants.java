package com.changlu.common.config;

/**
 * @ClassName Constants
 * @Author ChangLu
 * @Date 3/28/2022 10:43 AM
 * @Description 常量
 */
public class Constants {

    //验证码过期时间：2分钟
    public static final Integer CAPTCHA_EXPIRATION = 1;

    //验证码redis的key
    public static final String CAPTCHA_CODE_KEY = "captcha_codes:";

    /**
     * 令牌前缀
     */
    public static final String TOKEN_PREFIX = "Bearer ";

    /**
     * 管理员角色ID
     */
//    public static Long ROLE_MANAGE_ID = 2L;

    /**
     * 成员角色ID
     */
//    public static Long ROLE_MEMBER_ID = 3L;

    /**
     * 资源映射路径 前缀
     */
    public static final String RESOURCE_PREFIX = "/profile";

    /**
     * 表示管理员具有所有的权限
     */
    public static final String ADMIN_PERMISSION = "*:*:*";

}
