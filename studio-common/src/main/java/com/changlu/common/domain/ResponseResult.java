package com.changlu.common.domain;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @Author changlu
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseResult<T> {
    /**
     * 状态码
     */
    private Integer code;
    /**
     * 提示信息，如果有错误时，前端可以获取该字段进行提示
     */
    private String msg;
    /**
     * 查询到的结果数据，
     */
    private T data;

    public ResponseResult(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResponseResult(Integer code, T data) {
        this.code = code;
        this.data = data;
    }

    public static <T> ResponseResult<T> success(String msg, T data){
        return new ResponseResult<T>(200,msg,data);
    }

    public static <T> ResponseResult<T> success(T data){
        return new ResponseResult<T>(200,data);
    }

    public static <T> ResponseResult<T> error(String msg){
        return new ResponseResult<T>(500,msg,null);
    }

    public static <T> ResponseResult<T> error(Integer code, String msg){
        return new ResponseResult<T>(code,msg,null);
    }

    /**
     * 根据实际影响结果来判断是否操作成功
     * @param rows 影响行数
     * @return
     */
    public static ResponseResult toResponse(int rows){
        if (rows > 0) {
            return ResponseResult.success("成功",null);
        }else {
            return ResponseResult.error("操作失败");
        }
    }

    /**
     * 根据实际影响结果来判断是否操作成功
     * @param bool 结果
     * @return
     */
    public static ResponseResult toResponse(boolean bool){
        if (bool) {
            return ResponseResult.success("成功",null);
        }else {
            return ResponseResult.error("操作失败");
        }
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public ResponseResult(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
}
