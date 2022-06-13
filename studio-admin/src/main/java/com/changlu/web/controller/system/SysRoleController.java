package com.changlu.web.controller.system;

import com.changlu.common.constant.UserConstants;
import com.changlu.domain.LoginUser;
import com.changlu.common.domain.ResponseResult;
import com.changlu.security.util.SecurityUtils;
import com.changlu.common.utils.StringUtils;
import com.changlu.service.ISysUserService;
import com.changlu.system.pojo.SysRole;
import com.changlu.system.pojo.SysUser;
import com.changlu.system.pojo.SysUserRole;
import com.changlu.system.service.ISysRoleService;
import com.changlu.web.controller.BaseController;
import com.changlu.common.utils.page.TableDataInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName SysRoleController
 * @Author ChangLu
 * @Date 4/10/2022 10:04 PM
 * @Description 系统角色控制器
 */
@RestController
@RequestMapping("/api/system/role")
public class SysRoleController extends BaseController {

    @Autowired
    private ISysRoleService roleService;

    @Autowired
    private ISysUserService sysUserService;


    @GetMapping("/list")
    @PreAuthorize("@ss.hasPerm('system:role:list')")
    public TableDataInfo list(SysRole role)
    {
        startPage();
        List<SysRole> list = roleService.selectRoleList(role);
        return getDataTable(list);
    }

    /**
     * 状态修改
     */
    @PutMapping("/changeStatus")
    @PreAuthorize("@ss.hasPerm('system:role:changeStatus')")
    public ResponseResult changeStatus(@RequestBody SysRole role)
    {
        roleService.checkRoleAllowed(role);
        roleService.checkRoleDataScope(role.getRoleId());
        role.setUpdateBy(getUsername());
        if (roleService.updateRoleStatus(role) > 0) {
            return ResponseResult.success("修改成功！",null);
        }
        return ResponseResult.error("修改失败");
    }

    /**
     * 根据角色编号获取详细信息
     */
    @GetMapping(value = "/{roleId}")
    @PreAuthorize("@ss.hasPerm('system:role:query')")
    public ResponseResult getInfo(@PathVariable Long roleId)
    {
        roleService.checkRoleDataScope(roleId);
        return ResponseResult.success(roleService.selectRoleById(roleId));
    }

    /**
     * 新增角色
     */
    @PostMapping
    @PreAuthorize("@ss.hasPerm('system:role:add')")
    public ResponseResult add(@Validated @RequestBody SysRole role)
    {
        if (UserConstants.NOT_UNIQUE.equals(roleService.checkRoleNameUnique(role)))
        {
            return ResponseResult.error("新增角色'" + role.getRoleName() + "'失败，角色名称已存在");
        }
        else if (UserConstants.NOT_UNIQUE.equals(roleService.checkRoleKeyUnique(role)))
        {
            return ResponseResult.error("新增角色'" + role.getRoleName() + "'失败，角色权限已存在");
        }
        role.setCreateBy(getUsername());
        return ResponseResult.toResponse(roleService.insertRole(role));
    }

    /**
     * 修改保存角色
     */
    @PutMapping
    @PreAuthorize("@ss.hasPerm('system:role:edit')")
    public ResponseResult edit(@RequestBody SysRole role)
    {
        roleService.checkRoleAllowed(role);//不允许对admin角色继续权限修改操作（有一个判断逻辑）
        roleService.checkRoleDataScope(role.getRoleId());//判断当前角色是否存在（就是根据当前的传入的sysrole来查询角色对象看是否有该角色）
        //检测角色名（rolename）中文名称是否唯一
        if (UserConstants.NOT_UNIQUE.equals(roleService.checkRoleNameUnique(role)))
        {
            return ResponseResult.error("修改角色'" + role.getRoleName() + "'失败，角色名称已存在");
        }
        //检测rolekey是否唯一，也就是表示角色的英文键
        else if (UserConstants.NOT_UNIQUE.equals(roleService.checkRoleKeyUnique(role)))
        {
            return ResponseResult.error("修改角色'" + role.getRoleName() + "'失败，角色权限已存在");
        }
        role.setUpdateBy(getUsername());//设置更新者
        //updateRole逻辑过程：①修改角色表。②删除角色_菜单表中关于该角色的所有记录。③查询最新该角色关联的角色记录【有使用异常回滚注解】
        if (roleService.updateRole(role) > 0)
        {
            // 更新当前用户权限
            LoginUser loginUser = SecurityUtils.getLoginUser();
            if (StringUtils.isNotNull(loginUser.getUser()) && !loginUser.getUser().isAdmin())
            {
                //更新当前用户状态
                sysUserService.updateSysUser(loginUser.getUser(), true);
            }
            return ResponseResult.success("修改成功！");
        }
        return ResponseResult.error("修改角色'" + role.getRoleName() + "'失败，请联系管理员");
    }

    /**
     * 删除角色
     */
    @DeleteMapping("/{roleIds}")
    @PreAuthorize("@ss.hasPerm('system:role:remove')")
    public ResponseResult remove(@PathVariable Long[] roleIds)
    {
        return ResponseResult.toResponse((roleService.deleteRoleByIds(roleIds)));
    }

    /**
     * 查询已分配用户角色列表
     */
    @GetMapping("/authUser/allocatedList")
    @PreAuthorize("@ss.hasPerm('system:role:authUser:allocatedList')")
    public TableDataInfo allocatedList(SysUser user)
    {
        startPage();
        List<SysUser> list = sysUserService.selectAllocatedList(user);
        return getDataTable(list);
    }

    /**
     * 查询未分配用户角色列表
     */
    @GetMapping("/authUser/unallocatedList")
    @PreAuthorize("@ss.hasPerm('system:role:authUser:unallocatedList')")
    public TableDataInfo unallocatedList(SysUser user)
    {
        startPage();
        List<SysUser> list = sysUserService.selectUnallocatedList(user);
        return getDataTable(list);
    }

    /**
     * 取消授权用户
     */
    @PutMapping("/authUser/cancel")
    @PreAuthorize("@ss.hasPerm('system:role:authUser:cancel')")
    public ResponseResult cancelAuthUser(@RequestBody SysUserRole userRole)
    {
        return ResponseResult.toResponse(roleService.deleteAuthUser(userRole));
    }

    /**
     * 批量选择用户授权
     */
    @PutMapping("/authUser/selectAll")
    @PreAuthorize("@ss.hasPerm('system:role:authUser:selectAll')")
    public ResponseResult selectAuthUserAll(Long roleId, Long[] userIds)
    {
        roleService.checkRoleDataScope(roleId);
        return ResponseResult.toResponse(roleService.insertAuthUsers(roleId, userIds));
    }

    /**
     * 批量取消授权用户
     */
    @PutMapping("/authUser/cancelAll")
    @PreAuthorize("@ss.hasPerm('system:role:authUser:cancelAll')")
    public ResponseResult cancelAuthUserAll(Long roleId, Long[] userIds)
    {
        return ResponseResult.toResponse(roleService.deleteAuthUsers(roleId, userIds));
    }


}
