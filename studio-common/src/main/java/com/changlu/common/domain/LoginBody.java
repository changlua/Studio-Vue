package com.changlu.common.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName LoginBody
 * @Author ChangLu
 * @Date 3/28/2022 11:22 AM
 * @Description 登录请求体
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginBody implements Serializable {

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 验证码
     */
    private String code;

    /**
     * 唯一标识
     */
    private String uuid;

    /**
     * 邀请码
     */
    private String invitationCode;

}
