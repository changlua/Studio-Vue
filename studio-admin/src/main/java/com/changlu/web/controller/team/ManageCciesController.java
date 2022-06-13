package com.changlu.web.controller.team;

import com.changlu.common.utils.ExcelUtil;
import com.changlu.service.ZfManageCcieService;
import com.changlu.web.controller.BaseController;
import com.changlu.vo.manage.MCcieVo;
import com.changlu.common.utils.page.TableDataInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @ClassName ManageCcieController
 * @Author ChangLu
 * @Date 4/7/2022 2:35 PM
 * @Description 管理证书控制器
 */
@RestController
@RequestMapping("/api/team/ccie")
public class ManageCciesController extends BaseController {

    @Autowired
    private ZfManageCcieService zfManageCcieService;

    /**
     * 查询证书列表
     */
    @GetMapping("/list")
    @PreAuthorize("@ss.hasPerm('team:ccie:list')")
    public TableDataInfo list(MCcieVo mCcieVo)
    {
        startPage();
        List<MCcieVo> list = zfManageCcieService.selectZfCcieList(mCcieVo);
        return getDataTable(list);
    }

    @PostMapping("/export")
    @PreAuthorize("@ss.hasPerm('team:ccie:export')")
    public void export(MCcieVo mCcieVo, HttpServletResponse response) {
        List<MCcieVo> list = zfManageCcieService.selectZfCcieList(mCcieVo);
        ExcelUtil<MCcieVo> util = new ExcelUtil<>(MCcieVo.class);
        util.exportExcel(response, list, "证书列表");
    }


}
