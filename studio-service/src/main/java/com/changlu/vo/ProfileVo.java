package com.changlu.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName ProfileVo
 * @Author ChangLu
 * @Date 4/2/2022 7:15 PM
 * @Description 个人页vo
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfileVo implements Serializable {

    /** 用户昵称 */
    private String nickName;

    /** 手机号码 */
    private String phonenumber;

    /** 用户邮箱 */
    private String email;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /** 用户性别 */
    private String sex;

}
