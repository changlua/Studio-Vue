package com.changlu.vo.manage;

import com.changlu.common.annoation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName MCcieVo
 * @Author ChangLu
 * @Date 4/7/2022 2:41 PM
 * @Description 管理证书vo实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MCcieVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 获奖证书id
     */
    private Long ccieId;

    /**
     * 证书名称
     */
    @Excel(name = "证书名称")
    private String ccieName;

    /**
     * 证书图片
     */
    @Excel(name = "证书图片", height = 50, cellType = Excel.ColumnType.IMAGE)
    private String ccieImg;

    /**
     * 获奖时间
     */
    @Excel(name = "获奖时间", dateFormat = "yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date ccciGetTime;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 真实姓名
     */
    @Excel(name = "真实姓名")
    private String realName;

    /**
     * 年级id
     */
    private Long gradeId;

    /**
     * 年级
     */
    @Excel(name = "年级")
    private String gradeNum;

    /**
     * 专业id
     */
    private Long majorId;

    /**
     * 专业
     */
    @Excel(name = "专业")
    private String majorName;

    /** 请求参数 */
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)  //只允许序列化（json=>对象），在对象转json时忽略
    private Map<String, Object> params;

    public Map<String, Object> getParams() {
        if (params == null)
        {
            params = new HashMap<>(1);
        }
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }

}
