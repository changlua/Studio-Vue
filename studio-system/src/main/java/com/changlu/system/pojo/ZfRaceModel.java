package com.changlu.system.pojo;

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
@TableName("zf_race")
@ApiModel(value="ZfRaceModel对象", description="")
public class ZfRaceModel extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "竞赛主键id")
    @TableId("race_id")
    private Long raceId;

    @ApiModelProperty(value = "竞赛名称")
    @Excel(name = "竞赛名称", sort = 1)
    private String raceName;

    @ApiModelProperty(value = "竞赛成员（存储参与成员的id，使用,分割）")
    private String raceMembers;

    @ApiModelProperty(value = "竞赛获奖证书")
    @Excel(name = "获奖证书",height = 50, cellType = Excel.ColumnType.IMAGE, sort = 4)
    private String raceCcie;

    @ApiModelProperty(value = "竞赛开始时间")
    @JsonFormat(pattern="yyyy-MM-dd")
    @Excel(name = "竞赛开始时间", dateFormat = "yyyy-MM-dd HH:mm:ss", sort = 5)
    private Date raceBeginTime;

    @ApiModelProperty(value = "竞赛结束时间")
    @JsonFormat(pattern="yyyy-MM-dd")
    @Excel(name = "竞赛结束时间", dateFormat = "yyyy-MM-dd HH:mm:ss", sort = 6)
    private Date raceEndTime;

    @ApiModelProperty(value = "标识个人还是团队(1是个人,2是团队)")
    @Excel(name = "竞赛形式", readConverterExp = "1=个人,2=团队", sort = 2)
    private String raceFlag;

    @ApiModelProperty(value = "经验总结")
    private String raceSummarize;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "修改时间")
    private Date updateTime;


}
