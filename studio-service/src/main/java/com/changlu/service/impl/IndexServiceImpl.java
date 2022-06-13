package com.changlu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.changlu.common.utils.DateUtils;
import com.changlu.enums.UserStatusEnum;
import com.changlu.enums.ZfRaceTypeEnum;
import com.changlu.mapper.IndexCountMapper;
import com.changlu.mapper.ZfCcieMapper;
import com.changlu.mapper.ZfMajorMapper;
import com.changlu.mapper.ZfRaceMapper;
import com.changlu.service.IndexService;
import com.changlu.system.mapper.SysUserMapper;
import com.changlu.system.pojo.SysUser;
import com.changlu.system.pojo.ZfMajorModel;
import com.changlu.system.pojo.ZfRaceModel;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @ClassName IndexServiceImpl
 * @Author ChangLu
 * @Date 4/9/2022 2:23 PM
 * @Description 首页展示业务层
 */
@Service
public class IndexServiceImpl implements IndexService {

    @Resource
    private SysUserMapper userMapper;

    @Resource
    private ZfRaceMapper zfRaceMapper;

    @Resource
    private ZfCcieMapper zfCcieMapper;

    @Resource
    private ZfMajorMapper zfMajorMapper;

    @Resource
    private IndexCountMapper indexCountMapper;


    /**
     * 获取统计数据
     */
    @Override
    public List<Integer> getCounts() {
        //1、获取用户总数
        LambdaQueryWrapper<SysUser> userWrapper = new LambdaQueryWrapper<SysUser>()
                .eq(SysUser::getStatus, UserStatusEnum.ACTIVE.value())
                .ne(SysUser::getUserId, 1L);//系统管理员不计入总数
        Integer userCount = this.userMapper.selectCount(userWrapper);
        //2、获取项目总数(团队竞赛)
        LambdaQueryWrapper<ZfRaceModel> teamRaceWrapper = new LambdaQueryWrapper<ZfRaceModel>()
                .eq(ZfRaceModel::getRaceFlag, ZfRaceTypeEnum.RACE_TYPE_TEAM.value());
        Integer projectCount = zfRaceMapper.selectCount(teamRaceWrapper);
        //3、获取竞赛总数(个人竞赛)
        LambdaQueryWrapper<ZfRaceModel> ownRaceWrapper = new LambdaQueryWrapper<ZfRaceModel>()
                .eq(ZfRaceModel::getRaceFlag, ZfRaceTypeEnum.RACE_TYPE_OWN.value());
        Integer raceCount = zfRaceMapper.selectCount(ownRaceWrapper);
        //4、获取获奖总数
        Integer ccieCount = zfCcieMapper.selectCount(null);
        List<Integer> counts = Arrays.asList(userCount, projectCount, raceCount, ccieCount);
        return counts;
    }

    /**
     * 获取echarts的options数据，数据格式如下：
     *  testJSON: {
     *    "grades":{
     *      names: ["2019级", "2020级", "2021级", "2022级"],
     *      values:{
     *        races: [5, 20, 36, 10],
     *        ccies: [5, 20, 36, 10]
     *      }
     *    },
     *    "majors":{
     *      names: ["计算机应用技术", "物联网应用技术", "大数据技术", "移动应用开发","软件技术","计算机网络技术","VR虚拟现实"],
     *      values:{
     *        races: [5, 20, 36, 10, 10,15,20],
     *        ccies: [5, 20, 100, 10, 10,15,20]
     *      }
     *    }
     *  }
     **/
    @Override
    public Map<String, Object> getOptions() {
        Map<String,Object> optionsResult = new HashMap<>(2);
        //1、统计年级
        //1.1、names
        SimpleDateFormat format = new SimpleDateFormat("YYYY");
        Integer beginYear = Integer.valueOf(format.format(DateUtils.minusDate(new Date(), Calendar.YEAR, 3)));
        Integer endYear = Integer.valueOf(format.format(new Date()));
        List<String> gradeNames = new ArrayList<>(endYear - beginYear);
        for (int YEAR = beginYear; YEAR <= endYear; YEAR++) {
            gradeNames.add(YEAR + "年");
        }
        Map<String,Object> grades = new HashMap<>(2);
        optionsResult.put("grades", grades);
        grades.put("names", gradeNames);
        Map<String,Object> gradeValues = new HashMap<>(2);
        grades.put("values", gradeValues);
        //1.2、values：races
        gradeValues.put("races", indexCountMapper.countRacesByGrade(beginYear, endYear));
        //1.2、values：ccies
        gradeValues.put("ccies", indexCountMapper.countCciesByGrade(beginYear, endYear));

        //2、统计专业
        //2.1、names
        LambdaQueryWrapper<ZfMajorModel> majorWrapper = new LambdaQueryWrapper<>(ZfMajorModel.class)
                .select(ZfMajorModel::getMajorName)
                .orderByAsc(ZfMajorModel::getMajorId);//根据专业id来进行排序
        List<ZfMajorModel> majorModels = zfMajorMapper.selectList(majorWrapper);
        List<String> majorNames = majorModels.stream().map((major) -> major.getMajorName()).collect(Collectors.toList());
        Map<String,Object> majors = new HashMap<>(2);
        optionsResult.put("majors", majors);
        majors.put("names", majorNames);
        Map<String,Object> majorValues = new HashMap<>(2);
        majors.put("values", majorValues);
        //2.2、values：races
        majorValues.put("races", indexCountMapper.countRacesByMajor());
        //2.2、values：ccies
        majorValues.put("ccies", indexCountMapper.countCciesByMajor());
        return optionsResult;
    }
}
