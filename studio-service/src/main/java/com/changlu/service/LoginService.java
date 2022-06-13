package com.changlu.service;

import com.changlu.common.domain.LoginBody;
import com.changlu.common.domain.ResponseResult;

import java.util.Map;

/**
 * @ClassName LoginService
 * @Author ChangLu
 * @Date 3/25/2022 2:48 PM
 * @Description 业务层登录接口
 */
public interface LoginService {

    /**
     * 登录
     * @param user
     * @return
     */
    Map<String,String> doLogin(LoginBody user);

    /**
     * 校验验证码
     * @param code 验证码
     * @param uuid 存储redis的key
     */
    void verifyCaptcha(String code, String uuid);

    /**
     * 注册用户
     * @param loginBody 用户主体
     * @return
     */
    boolean registerUser(LoginBody loginBody);
}
