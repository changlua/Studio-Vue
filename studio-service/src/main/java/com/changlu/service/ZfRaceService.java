package com.changlu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.changlu.common.domain.ResponseResult;
import com.changlu.vo.race.RaceVo;
import com.changlu.system.pojo.ZfRaceModel;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ChangLu
 * @since 2022-03-30
 */
public interface ZfRaceService extends IService<ZfRaceModel> {

    /**
     * 查询ZfRace
     *
     * @param zfRaceModel ZfRace主键
     * @return ZfRace
     */
    List<RaceVo> selectZfRaceModelList(ZfRaceModel zfRaceModel);

    /**
     * 根据用户id查询ZfRace列表
     *
     * @param zfRaceModel ZfRace
     * @return ZfRace集合
     */
    List<RaceVo> selectZfRaceModelListByUserId(ZfRaceModel zfRaceModel);

    /**
     * 查询ZfRace
     *
     * @param raceId ZfRace主键
     * @return ZfRace
     */
    RaceVo selectZfRaceModelByRaceId(Long raceId);

    /**
     * 新增ZfRace(个人)
     *
     * @param raceVo ZfRace
     * @return 结果
     */
    boolean insertZfRaceModel(RaceVo raceVo);

    /**
     * 修改ZfRace
     *
     * @param raceVo ZfRace
     * @return 结果
     */
    boolean updateZfRaceModel(RaceVo raceVo);


    /**
     * 删除ZfRace信息
     *
     * @param raceIds ZfRace主键
     * @return 结果
     */
    boolean deleteZfRaceModelByRaceIds(Long[] raceIds);


    /**
     * 根据用户删除竞赛信息
     *
     * @param raceIds ZfRace主键
     * @return 结果
     */
    boolean deleteZfRaceModelByUserId(Long userId);
}
