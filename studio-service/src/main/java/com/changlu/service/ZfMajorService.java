package com.changlu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.changlu.common.domain.ResponseResult;
import com.changlu.system.pojo.ZfMajorModel;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ChangLu
 * @since 2022-03-30
 */
public interface ZfMajorService extends IService<ZfMajorModel> {

    /**
     * 查询ZfMajor列表
     *
     * @param zfMajorModel ZfMajor
     * @return ZfMajor集合
     */
    List<ZfMajorModel> selectZfMajorModelList(ZfMajorModel zfMajorModel);

    /**
     * 查询ZfMajor
     *
     * @param majorId ZfMajor主键
     * @return ZfMajor
     */
    ZfMajorModel selectZfMajorModelByMajorId(Long majorId);

    /**
     * 新增ZfMajor
     *
     * @param zfMajorModel ZfMajor
     * @return 结果
     */
    int insertZfMajorModel(ZfMajorModel zfMajorModel);

    /**
     * 修改ZfMajor
     *
     * @param zfMajorModel ZfMajor
     * @return 结果
     */
    int updateZfMajorModel(ZfMajorModel zfMajorModel);

    /**
     * 批量删除ZfMajor
     *
     * @param majorIds 需要删除的ZfMajor主键集合
     * @return 结果
     */
    int deleteZfMajorModelByMajorIds(Long[] majorIds);
}
