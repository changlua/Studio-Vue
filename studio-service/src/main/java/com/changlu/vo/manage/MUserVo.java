package com.changlu.vo.manage;

import com.changlu.common.annoation.Excel;
import com.changlu.common.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName MUserVo
 * @Author ChangLu
 * @Date 4/5/2022 5:43 PM
 * @Description 工作室管理-用户视图
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MUserVo extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 用户角色id（允许多个，该字段主要用于查询）
     */
    private String roleIds;

    /**
     * 用户角色：连表
     */
    @Excel(name = "角色", sort = 0)
    private String roleName;

    /**
     * 真实姓名
     */
    @Excel(name = "姓名", sort = 1)
    private String realName;

    /** 用户账号名 */
    private String userName;

    /**
     * 年级id
     */
    private Long gradeId;

    /**
     * 年级名称：连表
     */
    @Excel(name = "年级", sort = 2)
    private String gradeNum;

    /**
     * 专业id
     */
    private Long majorId;

    /**
     * 专业名称：连表
     */
    @Excel(name = "专业名称", sort = 3)
    private String majorName;

    /**
     * 手机号码
     */
    @Excel(name = "手机号码", sort = 5)
    private String phonenumber;

    /**
     * 用户性别
     */
    @Excel(name = "性别", readConverterExp = "0=男,1=女,2=未知", sort = 4)
    private String sex;

    /**
     * 邮箱
     */
    @Excel(name = "邮箱", sort = 6)
    private String email;

    /**
     * 帐号状态（0正常 1停用）
     */
    @Excel(name = "账号状态", readConverterExp = "0=正常,1=停用", sort = 7)
    private String status;


    /** 个人照片 */
    private String perImg;

    /** 个人描述 */
    private String description;

}
