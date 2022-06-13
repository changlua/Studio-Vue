package com.changlu.common.exception;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

/**
 * @ClassName ServiceException
 * @Author ChangLu
 * @Date 3/29/2022 11:17 AM
 * @Description 业务异常处理返回
 */
public final class ServiceException extends RuntimeException{

    /**
     * 错误提示：这里异常自定义message。
     * 主要目的是防止框架中给我们抛异常时带上对应的报错异常父类信息（场景：loadUserByUsername中抛出来，若是不自定义那么就会出现如：org.springframkw.xxx 用户名或密码错误）。
     * 因为是从全局异常那里直接返回给用户提示信息的，所以不能带上类信息，所以我们自定义一个message属性，来最后响应给前端
     */
    private String message;

    /**
     * 空构造方法，避免反序列化问题
     */
    public ServiceException()
    {
    }

    public ServiceException(String message)  {
        this.message = message;
    }

    public ServiceException(String message,Throwable ex)
    {
        super(ex);
        this.message = message;
    }

    public String getMessage()
    {
        return message;
    }

}
