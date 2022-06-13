package com.changlu.mapper;


import com.changlu.vo.manage.MCcieVo;

import java.util.List;

/**
 * @ClassName ZfUserMapper
 * @Author ChangLu
 * @Date 4/5/2022 6:00 PM
 * @Description 用户管理接口
 */
public interface ZfMCcieMapper {

    /**
     * 查询所有证书列表
     */
    List<MCcieVo> selectZfCcieModelList(MCcieVo ccieVo);
}
