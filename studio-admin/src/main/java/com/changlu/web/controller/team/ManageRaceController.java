package com.changlu.web.controller.team;

import com.changlu.common.domain.ResponseResult;
import com.changlu.common.utils.ExcelUtil;
import com.changlu.mapper.ZfMUserMapper;
import com.changlu.service.ZfManageRaceService;
import com.changlu.web.controller.BaseController;
import com.changlu.vo.race.RaceVo;
import com.changlu.common.utils.page.TableDataInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @ClassName ManageRaceController
 * @Author ChangLu
 * @Date 4/7/2022 6:55 PM
 * @Description 管理竞赛控制器
 */
@RestController
@RequestMapping("/api/team/race")
public class ManageRaceController extends BaseController {

    @Autowired
    private ZfManageRaceService zfManageRaceService;

    @Resource
    private ZfMUserMapper zfMUserMapper;

    /**
     * 查询ZfRace列表
     */
    @GetMapping("/list")
    @PreAuthorize("@ss.hasPerm('team:race:list')")
    public TableDataInfo list(RaceVo raceVo)
    {
        startPage();
        List<RaceVo> list = zfManageRaceService.selectZfRaceModelList(raceVo);
        return getDataTable(list);
    }

    /**
     * 获取用户选项（id与姓名）
     */
    @GetMapping("/memberoptions")
    @PreAuthorize("@ss.hasPerm('team:race:memberoptions')")
    public ResponseResult getUserIdAndRealName(){
        return ResponseResult.success(zfMUserMapper.selectSysUserIdAndRealName());
    }

//    /**
//     * 查询用户的年级、专业信息（暂未使用）
//     */
//    @GetMapping("/member/{userIds}")
//    public ResponseResult membersGradeMajor(@PathVariable(name = "userIds") Long[] userIds)
//    {
//        return ResponseResult.success(zfMUserMapper.selectSysUserByUserIds(userIds));
//    }

    /**
     * 导出竞赛记录
     */
    @PostMapping("/export")
    @PreAuthorize("@ss.hasPerm('team:race:export')")
    public void export(RaceVo raceVo, HttpServletResponse response){
        List<RaceVo> list = zfManageRaceService.selectZfRaceModelList(raceVo);
        ExcelUtil<RaceVo> util = new ExcelUtil<>(RaceVo.class);
        util.exportExcel(response, list, "竞赛记录");
    }


}
