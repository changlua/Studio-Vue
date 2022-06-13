package com.changlu.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName ShowUserVo
 * @Author ChangLu
 * @Date 4/9/2022 5:36 PM
 * @Description 展示用户信息实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShowUserVo implements Serializable {

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 角色姓名
     */
    private String roleName;

    /**
     *  个人照片
     */
    private String perImg;

    /**
     * 年级名
     */
    private Integer gradeNum;

    /**
     * 专业名
     */
    private String majorName;

    /**
     * 描述
     */
    private String description;

}
