package com.changlu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.changlu.system.pojo.ZfResourceModel;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ChangLu
 * @since 2022-03-30
 */
public interface ZfResourceMapper extends BaseMapper<ZfResourceModel> {


    int deleteResources(@Param("resFlag")String resFlag,@Param("tableIds") Long[] tableIds);

}
