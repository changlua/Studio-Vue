package com.changlu.security.handler;

import com.alibaba.fastjson.JSON;
import com.changlu.common.domain.ResponseResult;
import com.changlu.security.service.TokenService;
import com.changlu.common.utils.JwtUtil;
import com.changlu.common.utils.RedisCache;
import com.changlu.common.utils.ServletUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName LogoutSuccessHandlerImpl
 * @Author ChangLu
 * @Date 3/25/2022 5:16 PM
 * @Description 注销成功处理器
 */

//方案二：自定义成功注销处理器
@Component
public class LogoutSuccessHandlerImpl implements LogoutSuccessHandler {

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private TokenService tokenService;

    //由于没有走jwtfilter，所以需要自己手动从header中取出token，取得subject
    //保证最后返回200状态码即可，不然前端就会无限发请求进入死循环
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String token = tokenService.getToken(request);
        try {
            //1、获取token中的登录标识uuid
            String uuid = JwtUtil.parseJWT(token).getSubject();
            if (!ObjectUtils.isEmpty(uuid)) {
                //若是有直接删除掉id
                redisCache.deleteObject("login:" + uuid);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //2、直接返回注销成功即可
        ServletUtils.renderString(response, JSON.toJSONString(new ResponseResult(200, "注销成功！")));
    }
}
