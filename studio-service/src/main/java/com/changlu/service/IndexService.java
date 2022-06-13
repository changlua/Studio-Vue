package com.changlu.service;

import java.util.List;
import java.util.Map;

/**
 * @ClassName IndexService
 * @Author ChangLu
 * @Date 4/9/2022 2:21 PM
 * @Description 首页数据业务层
 */
public interface IndexService {

    /**
     * 统计数据
     */
    List<Integer> getCounts();

    /**
     * 统计根据年级、专业划分统计出来的竞赛、证书数据
     */
    Map<String,Object> getOptions();

}
