package com.changlu.vo.race;

import com.changlu.common.annoation.Excel;
import com.changlu.system.pojo.ZfRaceModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @ClassName RaceVo
 * @Author ChangLu
 * @Date 4/4/2022 2:22 PM
 * @Description 竞赛视图层实例
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RaceVo extends ZfRaceModel {

    /**
     * 竞赛图片（多张，关联zf_resources）
     */
    private List<ResourceVo> pics;

    //管理团队页面使用
    /**
     * 团队成员名单：例如  xxx,xxx,xxx
     */
    @Excel(name = "参与成员", sort = 3)
    private String teamMemberNames;

}
