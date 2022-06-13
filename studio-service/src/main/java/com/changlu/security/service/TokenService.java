package com.changlu.security.service;

import com.changlu.common.config.Constants;
import com.changlu.domain.LoginUser;
import com.changlu.common.utils.JwtUtil;
import com.changlu.common.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName TokenService
 * @Author ChangLu
 * @Date 3/28/2022 1:38 PM
 * @Description Token业务处理
 */
@Service
public class TokenService {

    @Autowired
    private RedisCache redisCache;

    /**
     * 获取请求token
     *
     * @param request
     * @return token
     */
    public String getToken(HttpServletRequest request)
    {
        String token = request.getHeader(JwtUtil.header);
        if (!ObjectUtils.isEmpty(token) && token.startsWith(Constants.TOKEN_PREFIX))
        {
            token = token.replace(Constants.TOKEN_PREFIX, "");
        }
        return token;
    }

    /**
     * 检测当前该用户的登录存储信息是否在20分钟内过期
     * @param id
     */
    public void verifyToken(String id){
        LoginUser loginUser = redisCache.getCacheObject("login:" + id);
        long nowTime = System.currentTimeMillis();
        Long expireTime = loginUser.getExpireTime();
        //若是过期时间小于20分钟，进行刷新token
        if (((expireTime - nowTime) / JwtUtil.MILLIS_MINUTE) < 30){
            refreshToken(loginUser);
        }
    }

    /**
     * 刷新Token过期时间：本质我们这里是对redis进行存储
     */
    public void refreshToken(LoginUser loginUser){
        //用户对象中保存登录时间以及过期时间
        loginUser.setLoginTime(System.currentTimeMillis());
        loginUser.setExpireTime(loginUser.getLoginTime() + JwtUtil.JWT_EXPIRETIME * JwtUtil.MILLIS_MINUTE);
        //存储到redis中，key为：loginUser中的token(uuid)
//        String userId = loginUser.getUser().getUserId().toString();
        String token = loginUser.getToken();
        redisCache.setCacheObject("login:" + token, loginUser, JwtUtil.JWT_EXPIRETIME, TimeUnit.MINUTES);
    }

}
