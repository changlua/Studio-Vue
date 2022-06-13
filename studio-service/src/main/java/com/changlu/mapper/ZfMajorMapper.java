package com.changlu.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.changlu.system.pojo.ZfMajorModel;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ChangLu
 * @since 2022-03-30
 */
public interface ZfMajorMapper extends BaseMapper<ZfMajorModel> {

    /**
     * 查询ZfMajor列表
     *
     * @param zfMajorModel ZfMajor
     * @return ZfMajor集合
     */
    List<ZfMajorModel> selectZfMajorModelList(ZfMajorModel zfMajorModel);;

    /**
     * 查询ZfMajor
     *
     * @param majorId ZfMajor主键
     * @return ZfMajor
     */
    public ZfMajorModel selectZfMajorModelByMajorId(Long majorId);

    /**
     * 新增ZfMajor
     *
     * @param zfMajorModel ZfMajor
     * @return 结果
     */
    public int insertZfMajorModel(ZfMajorModel zfMajorModel);

    /**
     * 修改ZfMajor
     *
     * @param zfMajorModel ZfMajor
     * @return 结果
     */
    public int updateZfMajorModel(ZfMajorModel zfMajorModel);

    /**
     * 批量删除ZfMajor
     *
     * @param majorIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteZfMajorModelByMajorIds(Long[] majorIds);
}
