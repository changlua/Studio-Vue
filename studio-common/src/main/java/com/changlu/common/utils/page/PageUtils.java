package com.changlu.common.utils.page;

import com.changlu.common.utils.sql.SqlUtils;
import com.github.pagehelper.PageHelper;
import org.springframework.util.ObjectUtils;

/**
 * @ClassName PageUtils
 * @Author ChangLu
 * @Date 4/3/2022 2:43 PM
 * @Description 分页工具类
 */
public class PageUtils {

    /**
     * 开启分页
     */
    public static void startPage(){
        //1、从请求中构建出一个分页对象
        PageDomain pageDomain = TableSupport.buildPageRequest();
        //2、设置分页页以及每页分页数量
        final Integer pageNum = pageDomain.getPageNum();
        final Integer pageSize = pageDomain.getPageSize();
        if (!ObjectUtils.isEmpty(pageNum) && !ObjectUtils.isEmpty(pageSize)) {
            //3、校验是否有sql注入
            String orderBy = SqlUtils.escapeOrderBySql(pageDomain.getOrderBy());
            Boolean reasonable = pageDomain.getReasonable();
            //4、PageHelper底层使用了ThreadLocal来临时存储要进行分页的对象
            PageHelper.startPage(pageNum,pageSize,orderBy).setReasonable(reasonable);
        }
    }

}
