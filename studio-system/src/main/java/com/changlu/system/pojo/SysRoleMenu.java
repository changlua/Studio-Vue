package com.changlu.system.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.changlu.common.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 角色和菜单关联 sys_role_menu
 *
 * @author ruoyi
 */
@TableName(value="sys_role_menu")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysRoleMenu  extends BaseEntity
{
    /** 角色ID */
    @TableId
    private Long roleId;

    /** 菜单ID */
    private Long menuId;
}
