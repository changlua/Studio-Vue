package com.changlu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.changlu.common.domain.ResponseResult;
import com.changlu.common.utils.DateUtils;
import com.changlu.mapper.ZfThinkMapper;
import com.changlu.security.util.SecurityUtils;
import com.changlu.service.ZfThinkService;
import com.changlu.system.pojo.ZfThinkModel;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
public class ZfThinkServiceImpl extends ServiceImpl<ZfThinkMapper, ZfThinkModel> implements ZfThinkService {

    @Resource
    private ZfThinkMapper zfThinkModelMapper;

    /**
     * 查询ZfThink列表
     *
     * @param zfThinkModel ZfThink
     * @return ZfThink
     */
    @Override
    public List<ZfThinkModel> selectZfThinkModelList(ZfThinkModel zfThinkModel)
    {
        return zfThinkModelMapper.selectZfThinkModelList(zfThinkModel);
    }

    /**
     * 根据用户id来查询ZfThink列表
     *
     * @param zfThinkModel ZfThink
     * @return ZfThink
     */
    @Override
    public List<ZfThinkModel> selectZfThinkModelListByUserId(ZfThinkModel zfThinkModel) {
        zfThinkModel.setUserId(SecurityUtils.getUserId());
        return zfThinkModelMapper.selectZfThinkModelListByUserId(zfThinkModel);
    }

    /**
     * 查询ZfThink
     *
     * @param thinkId ZfThink主键
     * @return ZfThink
     */
    @Override
    public ZfThinkModel selectZfThinkModelByThinkId(Long thinkId)
    {
        return zfThinkModelMapper.selectZfThinkModelByThinkId(thinkId);
    }

    /**
     * 新增ZfThink
     *
     * @param zfThinkModel ZfThink
     * @return 结果
     */
    @Override
    public int insertZfThinkModel(ZfThinkModel zfThinkModel)
    {
        zfThinkModel.setCreateTime(DateUtils.getNowDate());
        //设置用户id
        zfThinkModel.setUserId(SecurityUtils.getUserId());
        return zfThinkModelMapper.insertZfThinkModel(zfThinkModel);
    }

    /**
     * 修改ZfThink
     *
     * @param zfThinkModel ZfThink
     * @return 结果
     */
    @Override
    public int updateZfThinkModel(ZfThinkModel zfThinkModel)
    {
        zfThinkModel.setUpdateTime(DateUtils.getNowDate());
        return zfThinkModelMapper.updateZfThinkModel(zfThinkModel);
    }

    /**
     * 批量删除ZfThink
     *
     * @param thinkIds 需要删除的ZfThink主键
     * @return 结果
     */
    @Override
    public int deleteZfThinkModelByThinkIds(Long[] thinkIds)
    {
        return zfThinkModelMapper.deleteZfThinkModelByThinkIds(thinkIds);
    }

}
