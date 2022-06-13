package com.changlu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.changlu.vo.race.ResourceVo;
import com.changlu.enums.ZfResourceEnum;
import com.changlu.system.pojo.ZfResourceModel;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ChangLu
 * @since 2022-03-30
 */
public interface ZfResourceService extends IService<ZfResourceModel> {

    /**
     * 插入多条资源记录
     */
    boolean insertResources(ZfResourceEnum resFlag, Long tableId, List<ResourceVo> pics);

    /**
     * 删除指定关联表id的资源记录
     */
    int deleteResources(ZfResourceEnum resFlag, Long[] tableIds);

}
