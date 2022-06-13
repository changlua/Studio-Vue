package com.changlu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.changlu.common.domain.ResponseResult;
import com.changlu.system.pojo.ZfCcieModel;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ChangLu
 * @since 2022-03-30
 */
public interface ZfCcieService extends IService<ZfCcieModel> {

    /**
     * 查询ZfCcie列表
     *
     * @param ccieModel
     * @return ZfCcie集合
     */
    List<ZfCcieModel> selectZfCcieList(ZfCcieModel ccieModel);

    /**
     * 查询ZfCcie列表根据用户id
     *
     * @param ccieModel
     * @return ZfCcie集合
     */
    List<ZfCcieModel> selectZfCcieListByUserId(ZfCcieModel ccieModel);

    /**
     * 查询ZfCcie
     *
     * @param ccieId ZfCcie主键
     * @return ZfCcie
     */
    ZfCcieModel selectZfCcieByCcieId(Long ccieId);

    /**
     * 新增ZfCcie
     *
     * @param zfCcie ZfCcie
     * @return 结果
     */
     int insertZfCcie(ZfCcieModel zfCcie);

    /**
     * 修改ZfCcie
     *
     * @param zfCcie ZfCcie
     * @return 结果
     */
    int updateZfCcie(ZfCcieModel zfCcie);

    /**
     * 批量删除ZfCcie
     *
     * @param ccieIds 需要删除的ZfCcie主键集合
     * @return 结果
     */
    int deleteZfCcieByCcieIds(Long[] ccieIds);

    /**
     * 删除ZfCcie信息
     *
     * @param ccieId ZfCcie主键
     * @return 结果
     */
    ResponseResult deleteZfCcieByCcieId(Long ccieId);

}
