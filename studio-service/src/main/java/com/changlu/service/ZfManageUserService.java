package com.changlu.service;

import com.changlu.common.domain.ResponseResult;
import com.changlu.vo.manage.MUserVo;

import java.util.List;

/**
 * @ClassName ZfManageUserService
 * @Author ChangLu
 * @Date 4/5/2022 5:55 PM
 * @Description 管理员用户业务接口
 */
public interface ZfManageUserService {


    /**
     * 查询用户接口
     * @param userVo
     * @return
     */
    List<MUserVo> selectSysUserList(MUserVo userVo);

    /**
     * 获取用户信息表
     * @param userId
     * @return
     */
    MUserVo selectSysUserByUserId(Long userId);

    /**
     * 更新用户信息
     * @param userVo
     * @return
     */
    int updateSysUser(MUserVo userVo);

    /**
     * 注销账号
     * @param userId
     * @return
     */
    boolean cancelledSysUser(Long userId);

    /**
     * 转让负责人
     * @param targetUserId
     * @return
     */
    boolean transfer(Long targetUserId);

    /**
     * 重置指定账号密码
     * @param targetUserId
     * @return
     */
    boolean resetPwd(Long[] targetUserId);

    /**
     * 删除指定用户账号
     * @param targetUserId
     * @return
     */
    boolean delete(Long targetUserId);

    /**
     * 激活用户账号
     * @param userId
     * @return
     */
    boolean activeSysUser(Long userId);
}
