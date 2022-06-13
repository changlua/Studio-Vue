package com.changlu.web.controller;

import com.changlu.security.util.SecurityUtils;
import com.changlu.common.utils.page.PageUtils;
import com.changlu.common.utils.page.TableDataInfo;
import com.github.pagehelper.PageInfo;

import java.util.List;


/**
 * @ClassName BaseController
 * @Author ChangLu
 * @Date 4/3/2022 2:42 PM
 * @Description 公用的controller方法
 */
public class BaseController {

    /**
     * 开启分页：自动会去request请求中查询并且进行设置
     */
    protected void startPage(){
        PageUtils.startPage();
    }

    /**
     * 封装成表格对象返回
     * @param list
     * @return
     */
    protected TableDataInfo getDataTable(List<?> list){
        TableDataInfo respData = new TableDataInfo();
        respData.setCode(200);
        respData.setMsg("查询成功!");
        respData.setRows(list);
        respData.setTotal(new PageInfo(list).getTotal());
        return respData;
    }

    /**
     * 自定义表格数量（解决PageHelper给我们误查总数情况）
     * @param list
     * @param count
     * @return
     */
    protected TableDataInfo getDataTable(List<?> list, Long count){
        TableDataInfo respData = new TableDataInfo();
        respData.setCode(200);
        respData.setMsg("查询成功!");
        respData.setRows(list);
        respData.setTotal(count);
        return respData;
    }

    /**
     * 获取登录用户名
     */
    public String getUsername()
    {
        return SecurityUtils.getLoginUser().getUsername();
    }

    /**
     * 获取登录用户id
     */
    public Long getUserId()
    {
        return SecurityUtils.getLoginUser().getUser().getUserId();
    }

}
