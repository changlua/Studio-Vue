package com.changlu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.changlu.common.domain.ResponseResult;
import com.changlu.mapper.ZfCcieMapper;
import com.changlu.security.util.SecurityUtils;
import com.changlu.service.ZfCcieService;
import com.changlu.system.pojo.ZfCcieModel;
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
public class ZfCcieServiceImpl extends ServiceImpl<ZfCcieMapper, ZfCcieModel> implements ZfCcieService {

    @Resource
    private ZfCcieMapper zfCcieMapper;

    @Override
    public List<ZfCcieModel> selectZfCcieList(ZfCcieModel ccieModel) {
        return zfCcieMapper.selectZfCcieList(ccieModel);
    }

    @Override
    public List<ZfCcieModel> selectZfCcieListByUserId(ZfCcieModel ccieModel) {
        //设置用户id
        ccieModel.setUserId(SecurityUtils.getUserId());
        return zfCcieMapper.selectZfCcieListByUserId(ccieModel);
    }

    @Override
    public ZfCcieModel selectZfCcieByCcieId(Long ccieId){
        return zfCcieMapper.selectZfCcieByCcieId(ccieId);
    }

    @Override
    public int insertZfCcie(ZfCcieModel zfCcie)
    {
        zfCcie.setUserId(SecurityUtils.getUserId());
        return zfCcieMapper.insertZfCcie(zfCcie);
    }

    /**
     * 修改ZfCcie
     *
     * @param zfCcie ZfCcie
     * @return 结果
     */
    @Override
    public int updateZfCcie(ZfCcieModel zfCcie)
    {
        return zfCcieMapper.updateZfCcie(zfCcie);
    }

    /**
     * 批量删除ZfCcie
     *
     * @param ccieIds 需要删除的ZfCcie主键
     * @return 结果
     */
    @Override
    public int deleteZfCcieByCcieIds(Long[] ccieIds)
    {
        return zfCcieMapper.deleteZfCcieByCcieIds(ccieIds);
    }

    @Override
    public ResponseResult deleteZfCcieByCcieId(Long ccieId) {
        int result = zfCcieMapper.deleteZfCcieByCcieId(ccieId);
        if (result > 0) {
            return ResponseResult.success("删除成功！");
        }
        return ResponseResult.error("删除失败！");
    }

}
