package com.changlu.web.controller;

import com.changlu.common.domain.ResponseResult;
import com.changlu.common.utils.RedisCache;
import com.changlu.web.config.ZfConstant;
import com.changlu.web.task.GenerateTeamUsersTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @ClassName CommonController
 * @Author ChangLu
 * @Date 4/10/2022 12:04 AM
 * @Description 常用控制器：用于对外暴露调用接口
 */
@RestController
@RequestMapping("/api/common")
public class CommonController {

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private GenerateTeamUsersTask genTeamUsersTask;

    @GetMapping("/members")
    public ResponseResult getMembersInfo(){
        List<Map> result = genTeamUsersTask.doGenerateTeamUsers();
        //走缓存
//        List<Map> result = redisCache.getCacheObject(ZfConstant.REDIS_MEMBERS_DATA);
//        if (ObjectUtils.isEmpty(result)) {
//            result = genTeamUsersTask.doGenerateTeamUsers();
//            redisCache.setCacheObject(ZfConstant.REDIS_MEMBERS_DATA, result);
//        }
        return ResponseResult.success(result);
    }

}
