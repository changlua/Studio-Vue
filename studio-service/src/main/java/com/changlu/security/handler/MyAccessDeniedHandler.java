package com.changlu.security.handler;

import com.alibaba.fastjson.JSON;
import com.changlu.common.domain.ResponseResult;
import com.changlu.common.utils.ServletUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName AccessDeniedHandlerImpl
 * @Author ChangLu
 * @Date 3/25/2022 7:27 PM
 * @Description 自定义授权异常执行器（未认证，403）
 */
@Component
public class MyAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        ResponseResult responseResult = new ResponseResult(HttpStatus.FORBIDDEN.value(), "当前账号无权限访问");
        ServletUtils.renderString(response, JSON.toJSONString(responseResult));
    }
}
