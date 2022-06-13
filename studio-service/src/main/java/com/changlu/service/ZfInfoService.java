package com.changlu.service;

import com.changlu.vo.InfoVo;

import java.util.List;
import java.util.Map;

/**
 * @ClassName ZfInfoService
 * @Author ChangLu
 * @Date 3/31/2022 1:00 PM
 * @Description 用户信息提交业务层
 */

public interface ZfInfoService {

    int commitUserInfo(InfoVo infoVo);

    InfoVo getUserInfo();

    Map<String, List> getMenu();
}
