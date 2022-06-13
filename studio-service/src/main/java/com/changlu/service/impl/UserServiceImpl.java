package com.changlu.service.impl;

import com.changlu.common.utils.DateUtils;
import com.changlu.service.IUserService;
import com.changlu.system.mapper.SysUserMapper;
import com.changlu.system.pojo.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName UserServiceImpl
 * @Author ChangLu
 * @Date 3/28/2022 10:39 PM
 * @Description 用户接口
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private SysUserMapper userMapper;

    @Override
    public String selectUserRoleGroup(SysUser user) {
        List<String> roles = userMapper.selectRolesByUserId(user.getUserId());
        return roles.stream().collect(Collectors.joining(","));
    }

    /**
     * 修改用户信息
     *
     * @param sysUser 用户信息
     * @return 结果
     */
    @Override
    public int updateSysUser(SysUser sysUser)
    {
        sysUser.setUpdateTime(DateUtils.getNowDate());
        return userMapper.updateSysUser(sysUser);
    }

}
