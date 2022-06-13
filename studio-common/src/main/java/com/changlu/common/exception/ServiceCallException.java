package com.changlu.common.exception;

/**
 * @ClassName ServiceCallException
 * @Author ChangLu
 * @Date 3/31/2022 11:21 AM
 * @Description 第三方服务调用异常
 */
public class ServiceCallException extends RuntimeException{

    public ServiceCallException(String msg,Throwable ex){
        super(msg,ex);
    }

}
