package com.changlu.web.controller.own;

import com.changlu.common.domain.ResponseResult;
import com.changlu.service.ZfThinkService;
import com.changlu.web.controller.BaseController;
import com.changlu.system.pojo.ZfThinkModel;
import com.changlu.common.utils.page.TableDataInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName ThinkController
 * @Author ChangLu
 * @Date 4/4/2022 5:51 PM
 * @Description 个人思考控制器
 */
@RestController
@RequestMapping("/api/own/think")
public class ThinkController extends BaseController {

    @Autowired
    private ZfThinkService zfThinkService;

    /**
     * 查询ZfThink列表
     */
    @GetMapping("/list")
    @PreAuthorize("@ss.hasPerm('own:think:list')")
    public TableDataInfo list(ZfThinkModel zfThinkModel)
    {
        startPage();
        List<ZfThinkModel> list = zfThinkService.selectZfThinkModelListByUserId(zfThinkModel);
        return getDataTable(list);
    }

    /**
     * 获取ZfThink详细信息
     */
    @GetMapping(value = "/{thinkId}")
    @PreAuthorize("@ss.hasPerm('own:think:query')")
    public ResponseResult getInfo(@PathVariable("thinkId") Long thinkId)
    {
        return ResponseResult.success(zfThinkService.selectZfThinkModelByThinkId(thinkId));
    }

    /**
     * 新增ZfThink
     */
    @PostMapping
    @PreAuthorize("@ss.hasPerm('own:think:add')")
    public ResponseResult add(@RequestBody ZfThinkModel zfThinkModel)
    {
        return ResponseResult.toResponse(zfThinkService.insertZfThinkModel(zfThinkModel));
    }

    /**
     * 修改ZfThink
     */
    @PutMapping
    @PreAuthorize("@ss.hasPerm('own:think:edit')")
    public ResponseResult edit(@RequestBody ZfThinkModel zfThinkModel)
    {
        return ResponseResult.toResponse(zfThinkService.updateZfThinkModel(zfThinkModel));
    }

    /**
     * 删除ZfThink
     */
    @DeleteMapping("/{thinkIds}")
    @PreAuthorize("@ss.hasPerm('own:think:remove')")
    public ResponseResult remove(@PathVariable Long[] thinkIds)
    {
        return ResponseResult.toResponse(zfThinkService.deleteZfThinkModelByThinkIds(thinkIds));
    }

}
