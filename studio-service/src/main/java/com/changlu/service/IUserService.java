package com.changlu.service;


import com.changlu.system.pojo.SysUser;

/**
 * @ClassName IUserService
 * @Author ChangLu
 * @Date 3/28/2022 10:37 PM
 * @Description 用户服务层
 */
public interface IUserService {

    /**
     * 根据用户id来查询用户所有角色
     * @param userId 用户id
     * @return
     */
    String selectUserRoleGroup(SysUser user);

    /**
     * 更新用户信息
     * @param sysUser 系统用户
     * @return
     */
    int updateSysUser(SysUser sysUser);
}
