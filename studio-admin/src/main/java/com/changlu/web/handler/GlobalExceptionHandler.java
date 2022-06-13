package com.changlu.web.handler;

import com.changlu.common.constant.HttpStatus;
import com.changlu.common.domain.ResponseResult;
import com.changlu.common.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName GlobalExceptionHandler
 * @Author ChangLu
 * @Date 3/29/2022 10:21 AM
 * @Description 全局捕捉异常处理器
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 权限校验异常
     */
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseResult handleAccessDeniedException(AccessDeniedException e, HttpServletRequest request)
    {
        String requestURI = request.getRequestURI();
        log.error("请求地址'{}',权限校验失败'{}'", requestURI, e.getMessage());
        return ResponseResult.error(HttpStatus.FORBIDDEN, "没有权限，请联系管理员授权");
    }

    /**
     * 请求方式不支持（针对于不支持的请求出现异常进行捕捉返回）
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseResult handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException e,
                                                              HttpServletRequest request)
    {
        String requestURI = request.getRequestURI();
        log.error("请求地址'{}',不支持'{}'请求", requestURI, e.getMethod());
        return ResponseResult.error(e.getMessage());
    }

    /**
     * 拦截自定义的服务异常，来进行统一返回
     */
    @ExceptionHandler(ServiceException.class)
    public ResponseResult handleServiceException(ServiceException e, HttpServletRequest request)
    {
        String requestURI = request.getRequestURI();
        log.error("请求地址'{}',发生服务异常.", requestURI, e);
        return ResponseResult.error(e.getMessage());
    }

    /**
     * 拦截未知的运行时异常
     */
    @ExceptionHandler(RuntimeException.class)
    public ResponseResult handleRuntimeException(RuntimeException e, HttpServletRequest request)
    {
        String requestURI = request.getRequestURI();
        log.error("请求地址'{}',发生未知异常.", requestURI, e);
//        return ResponseResult.error(e.getMessage());
        return ResponseResult.error("系统异常");
    }

    /**
     * 系统异常
     */
    @ExceptionHandler(Exception.class)
    public ResponseResult handleException(Exception e, HttpServletRequest request)
    {
        String requestURI = request.getRequestURI();
        log.error("请求地址'{}',发生系统异常.", requestURI, e);
        return ResponseResult.error("系统异常");
    }

}
