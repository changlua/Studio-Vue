package com.changlu.service;

import com.changlu.vo.manage.MCcieVo;

import java.util.List;

/**
 * @ClassName ZfManageCcieService
 * @Author ChangLu
 * @Date 4/7/2022 2:38 PM
 * @Description 管理证书业务接口
 */
public interface ZfManageCcieService {


    /**
     * 查询所有证书记录
     */
    List<MCcieVo> selectZfCcieList(MCcieVo ccieVo);

}
