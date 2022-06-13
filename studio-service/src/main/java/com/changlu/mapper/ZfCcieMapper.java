package com.changlu.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.changlu.system.pojo.ZfCcieModel;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ChangLu
 * @since 2022-03-30
 */
public interface ZfCcieMapper extends BaseMapper<ZfCcieModel> {

    /**
     * 查询ZfCcie列表
     *
     * @param zfCcie ZfCcie
     * @return ZfCcie集合
     */
    public List<ZfCcieModel> selectZfCcieList(ZfCcieModel zfCcie);

    /**
     * 根据用户id查询ZfCcie列表
     *
     * @param zfCcie ZfCcie
     * @return ZfCcie集合
     */
    public List<ZfCcieModel> selectZfCcieListByUserId(ZfCcieModel zfCcie);

    /**
     * 查询ZfCcie
     *
     * @param ccieId ZfCcie主键
     * @return ZfCcie
     */
    public ZfCcieModel selectZfCcieByCcieId(Long ccieId);

    /**
     * 新增ZfCcie
     *
     * @param zfCcie ZfCcie
     * @return 结果
     */
    public int insertZfCcie(ZfCcieModel zfCcie);

    /**
     * 修改ZfCcie
     *
     * @param zfCcie ZfCcie
     * @return 结果
     */
    public int updateZfCcie(ZfCcieModel zfCcie);

    /**
     * 删除ZfCcie
     *
     * @param ccieId ZfCcie主键
     * @return 结果
     */
    public int deleteZfCcieByCcieId(Long ccieId);


    /**
     * 批量删除ZfCcie
     *
     * @param ccieIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteZfCcieByCcieIds(Long[] ccieIds);

    /**
     * 根据用户id删除ZfCcie
     *
     * @param ccieId ZfCcie主键
     * @return 结果
     */
    public int deleteZfCcieByUserId(Long userId);

}
