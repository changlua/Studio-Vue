package com.changlu.system.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.changlu.common.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户和角色关联 sys_user_role
 *
 * @author ruoyi
 */
@TableName(value="sys_user_role")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysUserRole extends BaseEntity
{
    /** 用户ID */
    @TableId
    private Long userId;

    /** 角色ID */
    private Long roleId;
}
