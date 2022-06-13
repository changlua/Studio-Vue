package com.changlu.system.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.changlu.common.annoation.Excel;
import com.changlu.common.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author ChangLu
 * @since 2022-03-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("zf_ccie")
@ApiModel(value="ZfCcieModel对象", description="")
public class ZfCcieModel extends BaseEntity {

    @ApiModelProperty(value = "获奖证书主键id")
    @TableId(value = "ccie_id",type = IdType.AUTO)
    private Long ccieId;

    @ApiModelProperty(value = "证书名称")
    @TableField("ccie_name")
    @Excel(name = "证书名称")
    private String ccieName;

    @ApiModelProperty(value = "获奖证书图片")
    @TableField("ccie_img")
    @Excel(name = "获奖证书图片", cellType = Excel.ColumnType.IMAGE)
    private String ccieImg;

    @ApiModelProperty(value = "获奖时间")
    @TableField("ccci_get_time")
    @JsonFormat(pattern="yyyy-MM-dd")
    @Excel(name = "获奖时间", dateFormat = "yyyy-MM-dd")
    private Date ccciGetTime;

    @ApiModelProperty(value = "经验总结")
    @TableField("ccie_think")
    @Excel(name = "经验总结")
    private String ccieThink;

    @ApiModelProperty(value = "用户id")
    @TableField("user_id")
    private Long userId;


}
