package com.changlu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.changlu.mapper.ZfResourceMapper;
import com.changlu.service.ZfResourceService;
import com.changlu.vo.race.ResourceVo;
import com.changlu.enums.ZfResourceEnum;
import com.changlu.system.pojo.ZfResourceModel;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
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
public class ZfResourceServiceImpl extends ServiceImpl<ZfResourceMapper, ZfResourceModel> implements ZfResourceService {

    @Resource
    private ZfResourceMapper zfResourceMapper;

    /**
     * 插入多条资源
     * @param resFlag 资源的标识（指定哪张表，目前关联的表有竞赛表其标识为1）
     * @param tableId 关联对应的表记录id
     * @param pics 要保存的图片资源
     * @return
     */
    @Override
    public boolean insertResources(ZfResourceEnum resFlag, Long tableId, List<ResourceVo> pics) {
        //1、批量进行创建对象
        List<ZfResourceModel> resourceModels = new ArrayList<>(pics.size());
        pics.stream().forEach((pic)->{
            ZfResourceModel zfResourceModel = new ZfResourceModel();
            BeanUtils.copyProperties(pic, zfResourceModel);//拷贝资源原名、URL
            zfResourceModel.setResFlag(String.valueOf(resFlag.value()));//设置资源标识
            zfResourceModel.setTableId(tableId);//设置关联表的id（这里是竞赛记录id）
            zfResourceModel.setCreateTime(new Date());//设置创建时间
            resourceModels.add(zfResourceModel);
        });
        //2、批量更新
        return this.saveBatch(resourceModels);
    }

    @Override
    public int deleteResources(ZfResourceEnum resFlag, Long[] tableIds) {
        return zfResourceMapper.deleteResources(String.valueOf(resFlag.value()),tableIds);
    }
}
