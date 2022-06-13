package com.changlu.web.controller.own;

import com.changlu.common.domain.ResponseResult;
import com.changlu.service.ZfInfoService;
import com.changlu.vo.InfoVo;
import com.changlu.web.task.GenerateTeamUsersTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @ClassName InfoController
 * @Author ChangLu
 * @Date 3/31/2022 12:35 PM
 * @Description 信息录入控制器
 */
@RestController
@RequestMapping("/api/own/info")
public class InfoController {

    @Autowired
    private ZfInfoService infoService;

    @Autowired
    private GenerateTeamUsersTask genTeamUsersTask;

    @GetMapping("/getMenu")
    @PreAuthorize("@ss.hasPerm('own:info:menu')")
    public ResponseResult getMenu(){
        Map<String,List> result = infoService.getMenu();
        return ResponseResult.success(result);
    }

    @GetMapping
    @PreAuthorize("@ss.hasPerm('own:info:query')")
    public ResponseResult getUserInfo(){
        InfoVo userInfo = infoService.getUserInfo();
        return ResponseResult.success(userInfo);
    }

    @PostMapping
    @PreAuthorize("@ss.hasPerm('own:info:edit')")
    public ResponseResult commitUserInfo(@RequestBody InfoVo infoVO){
        return ResponseResult.toResponse(infoService.commitUserInfo(infoVO));
        //更新成功之后，我们来执行一次异步任务
//        genTeamUsersTask.doGenerateTask();
    }

}
