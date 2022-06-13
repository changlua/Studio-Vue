package com.changlu.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName PasswordVo
 * @Author ChangLu
 * @Date 4/2/2022 7:54 PM
 * @Description 修改密码视图层
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PasswordVo implements Serializable {

    //原本密码
    private String oldPassword;

    //新修改密码
    private String password;

}
