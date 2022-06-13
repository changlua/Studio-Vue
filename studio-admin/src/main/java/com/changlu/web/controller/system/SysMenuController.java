package com.changlu.web.controller.system;

import com.changlu.common.constant.UserConstants;
import com.changlu.common.domain.ResponseResult;
import com.changlu.common.utils.StringUtils;
import com.changlu.system.pojo.SysMenu;
import com.changlu.system.service.ISysMenuService;
import com.changlu.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName SysMenuController
 * @Author ChangLu
 * @Date 4/10/2022 10:40 PM
 * @Description 系统菜单控制器
 */
@RestController
@RequestMapping("/api/system/menu")
public class SysMenuController extends BaseController {

    @Autowired
    private ISysMenuService menuService;

    /**
     * 获取菜单列表
     */
    @GetMapping("/list")
    @PreAuthorize("@ss.hasPerm('system:menu:list')")
    public ResponseResult list(SysMenu menu)
    {
        //根据当前的用户权限来查询出所有的菜单
        List<SysMenu> menus = menuService.selectMenuList(menu, getUserId());
        return ResponseResult.success(menus);
    }

    /**
     * 获取菜单下拉树列表
     */
    @GetMapping("/treeselect")
    @PreAuthorize("@ss.hasPerm('system:menu:treeselect')")
    public ResponseResult treeselect(SysMenu menu)
    {
        //根据当前的调用接口的用户id查看到所有的菜单项
        List<SysMenu> menus = menuService.selectMenuList(menu, getUserId());
        return ResponseResult.success(menuService.buildMenuTreeSelect(menus));
    }

    /**
     * 加载对应角色菜单列表树
     */
    @GetMapping(value = "/roleMenuTreeselect/{roleId}")
    @PreAuthorize("@ss.hasPerm('system:menu:roleMenuTree')")
    public ResponseResult roleMenuTreeselect(@PathVariable("roleId") Long roleId)
    {
        List<SysMenu> menus = menuService.selectMenuList(getUserId());//若是管理员则查询出所有的菜单信息（根据当前用户的权限）
        Map<String,Object> result = new HashMap<>(2);
        //根据角色id获取菜单列表
        result.put("checkedKeys", menuService.selectMenuListByRoleId(roleId));//对应角色具备的菜单信息
        //根据用户菜单来构建菜单树
        result.put("menus", menuService.buildMenuTreeSelect(menus));
        return ResponseResult.success(result);
    }

    /**
     * 新增菜单
     */
    @PostMapping
    @PreAuthorize("@ss.hasPerm('system:menu:add')")
    public ResponseResult add(@Validated @RequestBody SysMenu menu)
    {
        //检测菜单的唯一性
        if (UserConstants.NOT_UNIQUE.equals(menuService.checkMenuNameUnique(menu)))
        {
            return ResponseResult.error("新增菜单'" + menu.getMenuName() + "'失败，菜单名称已存在");
        }
        //若是外链，那么检测是否http(s)开头
        else if (UserConstants.YES_FRAME.equals(menu.getIsFrame()) && !StringUtils.ishttp(menu.getPath()))
        {
            return ResponseResult.error("新增菜单'" + menu.getMenuName() + "'失败，地址必须以http(s)://开头");
        }
        menu.setCreateBy(getUsername());
        return ResponseResult.toResponse(menuService.insertMenu(menu));
    }

    /**
     * 根据菜单编号获取详细信息
     */
    @GetMapping(value = "/{menuId}")
    @PreAuthorize("@ss.hasPerm('system:menu:query')")
    public ResponseResult getInfo(@PathVariable Long menuId)
    {
        return ResponseResult.success(menuService.selectMenuById(menuId));
    }

    /**
     * 修改菜单
     */
    @PutMapping
    @PreAuthorize("@ss.hasPerm('system:menu:edit')")
    public ResponseResult edit(@Validated @RequestBody SysMenu menu)
    {
        if (UserConstants.NOT_UNIQUE.equals(menuService.checkMenuNameUnique(menu)))
        {
            return ResponseResult.error("修改菜单'" + menu.getMenuName() + "'失败，菜单名称已存在");
        }
        else if (UserConstants.YES_FRAME.equals(menu.getIsFrame()) && !StringUtils.ishttp(menu.getPath()))
        {
            return ResponseResult.error("修改菜单'" + menu.getMenuName() + "'失败，地址必须以http(s)://开头");
        }
        else if (menu.getMenuId().equals(menu.getParentId()))
        {
            return ResponseResult.error("修改菜单'" + menu.getMenuName() + "'失败，上级菜单不能选择自己");
        }
        menu.setUpdateBy(getUsername());
        return ResponseResult.toResponse(menuService.updateMenu(menu));
    }

    /**
     * 删除菜单
     */
    @DeleteMapping("/{menuId}")
    @PreAuthorize("@ss.hasPerm('system:menu:remove')")
    public ResponseResult remove(@PathVariable("menuId") Long menuId)
    {
        if (menuService.hasChildByMenuId(menuId))
        {
            return ResponseResult.error("存在子菜单,不允许删除");
        }
        if (menuService.checkMenuExistRole(menuId))
        {
            return ResponseResult.error("菜单已分配,不允许删除");
        }
        return ResponseResult.toResponse(menuService.deleteMenuById(menuId));
    }

}
