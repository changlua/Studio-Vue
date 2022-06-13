package com.changlu.security.service;

import com.changlu.common.config.Constants;
import com.changlu.security.util.SecurityUtils;
import com.changlu.system.pojo.SysRole;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

/**
 * @ClassName PermissionService
 * @Author ChangLu
 * @Date 4/11/2022 9:53 PM
 * @Description 权限业务层（用于鉴权）
 */
@Service("ss")
public class PermissionService {

    /**
     * 是否具备某个权限，如：system:user:list
     * @param permission 对应访问接口的权限
     * @return
     */
    public boolean hasPerm(String permission){
        //若是管理员权限则直接放行
        if (isAdmin()) {
            return true;
        }
        List<String> permissions = SecurityUtils.getLoginUser().getPermissions();
        return permissions.contains(permission);
    }

    /**
     * 是否具有某个角色：如：manage
     * @param role
     * @return
     */
    public boolean hasRole(String role) {
        //若是管理员权限则直接放行
        if (isAdmin()) {
            return true;
        }
        List<SysRole> roles = SecurityUtils.getUser().getRoles();
        if (!ObjectUtils.isEmpty(roles) && !ObjectUtils.isEmpty(role)) {
            for (SysRole sysRole : roles) {
                if (sysRole.getRoleKey().equals(role)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isAdmin(){
        List<String> permissions = SecurityUtils.getLoginUser().getPermissions();
        //若是管理员权限则直接放行
        if (permissions.contains(Constants.ADMIN_PERMISSION)) {
            return true;
        }
        return false;
    }

}
