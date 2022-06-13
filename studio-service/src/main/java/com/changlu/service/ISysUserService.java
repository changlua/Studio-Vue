package com.changlu.service;

import com.changlu.system.pojo.SysUser;

import java.util.List;

/**
 * @ClassName ISysUserService
 * @Author ChangLu
 * @Date 4/2/2022 7:22 PM
 * @Description 系统用户业务层接口
 */
public interface ISysUserService {

    /**
     * 更新系统用户
     * @param sysUser 系统用户对象
     * @return
     */
    int updateSysUser(SysUser sysUser, Boolean updateOwn);

    /**
     * 根据条件分页查询已分配用户角色列表
     *
     * @param user 用户信息
     * @return 用户信息集合信息
     */
    List<SysUser> selectAllocatedList(SysUser user);

    /**
     * 根据条件分页查询未分配用户角色列表
     *
     * @param user 用户信息
     * @return 用户信息集合信息
     */
    public List<SysUser> selectUnallocatedList(SysUser user);

}
