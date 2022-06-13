package com.changlu.service;

import com.changlu.vo.race.RaceVo;
import com.changlu.system.pojo.ZfRaceModel;

import java.util.List;

/**
 * @ClassName ZfManageRaceService
 * @Author ChangLu
 * @Date 4/7/2022 6:59 PM
 * @Description 管理竞赛业务层接口
 */
public interface ZfManageRaceService {

    List<RaceVo> selectZfRaceModelList(ZfRaceModel raceModel);
}
