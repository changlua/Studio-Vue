package com.changlu.web.task;

import com.changlu.common.utils.RedisCache;
import com.changlu.mapper.ZfMUserMapper;
import com.changlu.web.config.ZfConstant;
import com.changlu.vo.ShowUserVo;
import com.changlu.enums.ZfRoleEnum;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @ClassName GenerateTeamUsersTask
 * @Author ChangLu
 * @Date 4/9/2022 5:12 PM
 * @Description 生成团队用户信息
 */
@Component
@Slf4j
public class GenerateTeamUsersTask {

    @Resource
    private ZfMUserMapper zfMUserMapper;

    @Autowired
    private RedisCache redisCache;

//    @PostConstruct  //初始化servlet执行一次
    @Async("taskExecutor")  //执行异步任务
    public void doGenerateTask(){
        log.info(Thread.currentThread().getName()+ "commitUserInfo");
        List<Map> result = doGenerateTeamUsers();
        redisCache.setCacheObject(ZfConstant.REDIS_MEMBERS_DATA, result, 3, TimeUnit.MINUTES);
        log.info("已生成用户信息");
    }

    /**
     * 动态生成成员信息
     * @return
     * @throws JsonProcessingException
     */
    public List<Map> doGenerateTeamUsers() {
        List<ShowUserVo> showUserVos = zfMUserMapper.selectShowUserVoList();
        //3、构造响应对象
        List<Map> result = new ArrayList<>(showUserVos.size());
        //构建老师
        buildTeacherMembers(result, showUserVos);
        //构建学生
        buildStudentMembers(result, showUserVos);
        return result;
    }

    //构建指导老师列表
    public void buildTeacherMembers(List<Map> result, List<ShowUserVo> showUserVos){
        //获取到指导老师列表
        List<ShowUserVo> teachers = showUserVos.stream()
                .filter(user -> user.getRoleName() != null &&user.getRoleName().contains(ZfRoleEnum.ROLE_TEACHER.getName())) //只拿到指导老师
                .collect(Collectors.toList());
        if (teachers == null || teachers.size() == 0) {
            return;
        }
        for (ShowUserVo teacher : teachers) {
            if (ObjectUtils.isEmpty(teacher.getRoleName()) || //为空
                    teacher.getRoleName().contains(String.valueOf(ZfRoleEnum.ROLE_TEACHER.getName())) //包含"指导老师"
            ) {
                teacher.setRoleName(ZfRoleEnum.ROLE_TEACHER.getName());
            }
            //填充null为""
            if (teacher.getMajorName() == null){
                teacher.setMajorName("");
            }
        }
        Map<String, Object> teacherGroup = new HashMap<>(1);
        teacherGroup.put("grade", ZfRoleEnum.ROLE_TEACHER.getName());
        teacherGroup.put("members", teachers);
        result.add(teacherGroup);
    }

    //构建学生
    public void buildStudentMembers(List<Map> result, List<ShowUserVo> showUserVos){
        //1、对user字段进行替换（学生）
        List<ShowUserVo> studentUserVo = showUserVos.stream()
                .filter(user -> user.getRoleName() != null && !user.getRoleName().contains(ZfRoleEnum.ROLE_TEACHER.getName())) //去除指导老师
                .collect(Collectors.toList());
        for (ShowUserVo user: studentUserVo) {
            if (ObjectUtils.isEmpty(user.getRoleName())) {
                user.setRoleName("成员");
                continue;
            }
            if (user.getRoleName().contains(String.valueOf(ZfRoleEnum.ROLE_MANAGE.getName()))) {
                user.setRoleName(ZfRoleEnum.ROLE_MANAGE.getName());
            }
            //填充null为""
            if (user.getMajorName() == null){
                user.setMajorName("");
            }
        }
        //2、根据年级进行分组以及降序处理
        //key是年级，根据年级来进行降序排序
        TreeMap<Integer, List<ShowUserVo>> sortedMap = new TreeMap<>((o1, o2)-> o2-o1);//降序排序
        Map<Integer, List<ShowUserVo>> map = studentUserVo.stream().filter(userVo -> userVo.getGradeNum() != null) //过滤掉年级为null的
                .collect(Collectors.groupingBy(ShowUserVo::getGradeNum));//根据年级来进行分组
        sortedMap.putAll(map);
        //3、构造并添加学生
        for (Map.Entry<Integer, List<ShowUserVo>> entry : sortedMap.entrySet()) {
            Map<String,Object> gradeMap = new HashMap<>(2);
            Integer gradeNum = entry.getKey();
            List<ShowUserVo> members = sortedByRole(entry.getValue());
            gradeMap.put("grade", gradeNum + "级");
            gradeMap.put("members", members);
            result.add(gradeMap);
        }
    }

    /**
     * 将每个年级的负责人进行重新排序
     * @param members 年级的一组成员
     * @return
     */
    public static List<ShowUserVo> sortedByRole(List<ShowUserVo> members) {
        for (int i = 0; i < members.size(); i++) {
            ShowUserVo showUserVo = members.get(i);
            if (showUserVo.getRoleName().contains(ZfRoleEnum.ROLE_MANAGE.getName())) {
                ShowUserVo temp = showUserVo;
                members.set(i, members.get(0));
                members.set(0, temp);
                break;
            }
        }
        return members;
    }

}
