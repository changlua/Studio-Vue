package com.changlu.mapper;

import com.changlu.vo.ShowUserVo;
import com.changlu.vo.manage.MUserVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName ZfUserMapper
 * @Author ChangLu
 * @Date 4/5/2022 6:00 PM
 * @Description 用户管理接口
 */
public interface ZfMUserMapper {

    /**
     * 查询User列表
     *
     * @param userVo User
     * @return User集合
     */
    public List<MUserVo> selectSysUserList(MUserVo userVo);

    /**
     * 查询用于展示的用户数据列表
     */
    List<ShowUserVo> selectShowUserVoList();

    /**
     * 根据比赛id来查询到所有参与用户基本信息
     */
    List<ShowUserVo> selectRaceUsersByRaceId(Long raceId);

    /**
     * 查询所有用户的id以及真实姓名
     * @return
     */
    List<MUserVo> selectSysUserIdAndRealName();

    /**
     * 查询指定多个id的记录：包含基本信息有id、姓名、年级、年级id、专业、专业id
     * @return
     */
    List<MUserVo> selectSysUserByUserIds(Long[] ids);

    /**
     * 查询详细用户
     * @param userId
     * @return
     */
    MUserVo selectSysUserByUserId(Long userId);

    /**
     * 更新用户状态
     * @param userId
     * @return
     */
    int updateStatusByUserId(@Param("userId")Long userId,@Param("status") String status);

    /**
     * 重置用户密码
     * @param targetUserIds
     * @return
     */
    int resetPwdByUserId(@Param("targetUserIds") Long[] targetUserIds,@Param("password") String password);
}
