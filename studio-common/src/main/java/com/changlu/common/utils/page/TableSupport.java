package com.changlu.common.utils.page;

import com.changlu.common.utils.ServletUtils;

/**
 * @ClassName TableSupport
 * @Author ChangLu
 * @Date 4/3/2022 2:46 PM
 * @Description 表格数据处理
 */
public class TableSupport {

    /**
     * 当前配置起止索引
     */
    private static final String PAGE_NUM = "pageNum";

    /**
     * 每页显示记录数
     */
    private static final String PAGE_SIZE = "pageSize";

    /**
     * 排序列
     */
    private static final String ORDER_BY_COLUMN = "orderByColumn";

    /**
     * 排序的方向
     */
    private static final String IS_ASC = "isAsc";

    /**
     * 参数是否合理化
     */
    private static final String REASONABLE = "reasonable";

    /**
     * 封装分页对象
     * @return
     */
    private static PageDomain getPageDomain(){
        PageDomain pageDomain = new PageDomain();
        pageDomain.setPageNum(ServletUtils.getParameterToInt(PAGE_NUM));//当前页 pageNum
        pageDomain.setPageSize(ServletUtils.getParameterToInt(PAGE_SIZE));//每页的记录条数 pageSize
        pageDomain.setOrderByColumn(ServletUtils.getParameter(ORDER_BY_COLUMN));//根据某个字段来排序 orderByColumn
        pageDomain.setIsAsc(ServletUtils.getParameter(IS_ASC));//是否升序，默认是升序 isAsc
        pageDomain.setReasonable(ServletUtils.getParameterToBool(REASONABLE)); //分页参数合理化 reasonable
        return pageDomain;
    }

    public static PageDomain buildPageRequest()
    {
        return getPageDomain();
    }
}
