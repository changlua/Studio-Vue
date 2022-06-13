package com.changlu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.changlu.common.domain.ResponseResult;
import com.changlu.mapper.ZfMajorMapper;
import com.changlu.service.ZfMajorService;
import com.changlu.system.pojo.ZfMajorModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ChangLu
 * @since 2022-03-30
 */
@Service
public class ZfMajorServiceImpl extends ServiceImpl<ZfMajorMapper, ZfMajorModel> implements ZfMajorService {


    @Autowired
    private ZfMajorMapper majorMapper;

    /**
     * 查询ZfMajor列表
     *
     * @param zfMajorModel ZfMajor
     * @return ZfMajor
     */
    @Override
    public List<ZfMajorModel> selectZfMajorModelList(ZfMajorModel zfMajorModel)
    {
        return majorMapper.selectZfMajorModelList(zfMajorModel);
    }

    /**
     * 查询ZfMajor
     *
     * @param majorId ZfMajor主键
     * @return ZfMajor
     */
    @Override
    public ZfMajorModel selectZfMajorModelByMajorId(Long majorId)
    {
        return majorMapper.selectZfMajorModelByMajorId(majorId);
    }

    /**
     * 新增ZfMajor
     *
     * @param zfMajorModel ZfMajor
     * @return 结果
     */
    @Override
    public int insertZfMajorModel(ZfMajorModel zfMajorModel)
    {
        return majorMapper.insertZfMajorModel(zfMajorModel);
    }

    /**
     * 修改ZfMajor
     *
     * @param zfMajorModel ZfMajor
     * @return 结果
     */
    @Override
    public int updateZfMajorModel(ZfMajorModel zfMajorModel)
    {
        return majorMapper.updateZfMajorModel(zfMajorModel);
    }

    /**
     * 批量删除ZfMajor
     *
     * @param majorIds 需要删除的ZfMajor主键
     * @return 结果
     */
    @Override
    public int deleteZfMajorModelByMajorIds(Long[] majorIds)
    {
        return majorMapper.deleteZfMajorModelByMajorIds(majorIds);
    }

}
