package com.changlu.system.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.changlu.common.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * 用户对象 sys_user
 *
 * @author ruoyi
 */
@TableName(value="sys_user")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysUser extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 用户ID */
    @TableId(value = "user_id",type = IdType.AUTO)
    private Long userId;

    /** 用户账号 */
    private String userName;

    /** 用户昵称 */
    private String nickName;

    /** 用户类型 */
    private String userType;

    /** 用户邮箱 */
    private String email;

    /** 手机号码 */
    private String phonenumber;

    /** 用户性别 */
    private String sex;

    /** 用户头像 */
    private String avatar;

    /** 密码 */
    @JsonIgnore
    private String password;

    /** 帐号状态（0正常 1停用） */
    private String status;

    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;

    /** 最后登录IP */
    private String loginIp;

    /** 最后登录时间 */
    private Date loginDate;

    /** 角色对象 */
    private List<SysRole> roles;

    /** 角色ID */
    private Long roleId;

    /** 真实姓名 */
    private String realName;

    /** 个人描述 */
    private String description;

    /**  创建者  */
    private String createBy;

    /**  更新者  */
    private String updateBy;

    /**  备注  */
    private String remark;

    /** 个人照片 */
    private String perImg;

    //额外字段
    /** 专业id */
    private Long majorId;

    /** 年纪id */
    private Long gradeId;

    /** 专业名称 */
    private String majorName;

    /** 年级名 */
    private String gradeNum;

    public SysUser(String username,String nickName, String password){
        this.userName = username;
        this.nickName = nickName;
        this.password = password;
    }

    public static boolean isAdmin(Long userId)
    {
        return userId != null && 1L == userId;
    }

    public boolean isAdmin()
    {
        return isAdmin(this.userId);
    }
}
