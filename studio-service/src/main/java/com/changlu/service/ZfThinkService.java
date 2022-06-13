package com.changlu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.changlu.common.domain.ResponseResult;
import com.changlu.system.pojo.ZfThinkModel;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ChangLu
 * @since 2022-03-30
 */
public interface ZfThinkService extends IService<ZfThinkModel> {

    /**
     * 查询ZfThink列表
     *
     * @param zfThinkModel ZfThink
     * @return ZfThink集合
     */
     List<ZfThinkModel> selectZfThinkModelList(ZfThinkModel zfThinkModel);

    /**
     * 根据用户id查询ZfThink列表
     *
     * @param zfThinkModel ZfThink
     * @return ZfThink集合
     */
    List<ZfThinkModel> selectZfThinkModelListByUserId(ZfThinkModel zfThinkModel);

    /**
     * 查询ZfThink
     *
     * @param thinkId ZfThink主键
     * @return ZfThink
     */
    ZfThinkModel selectZfThinkModelByThinkId(Long thinkId);

    /**
     * 新增ZfThink
     *
     * @param zfThinkModel ZfThink
     * @return 结果
     */
    int insertZfThinkModel(ZfThinkModel zfThinkModel);

    /**
     * 修改ZfThink
     *
     * @param zfThinkModel ZfThink
     * @return 结果
     */
    int updateZfThinkModel(ZfThinkModel zfThinkModel);

    /**
     * 批量删除ZfThink
     *
     * @param thinkIds 需要删除的ZfThink主键集合
     * @return 结果
     */
    int deleteZfThinkModelByThinkIds(Long[] thinkIds);

}
