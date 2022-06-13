package com.changlu.common.utils.page;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName TableDataInfo
 * @Author ChangLu
 * @Date 4/3/2022 4:14 PM
 * @Description 表格数据对象
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TableDataInfo implements Serializable {

    //总记录数
    private long total;

    //表格内容
    private List<?> rows;

    //状态码
    private int code;

    //状态消息
    private String msg;

    /**
     * 分页对象
     * @param list 列表数据
     * @param total 总记录数
     */
    public TableDataInfo(List<?> list, int total){
        this.rows = list;
        this.total = total;
    }

}
