package com.changlu.service.impl;

import com.changlu.mapper.ZfMUserMapper;
import com.changlu.mapper.ZfRaceMapper;
import com.changlu.service.ZfManageRaceService;
import com.changlu.service.ZfRaceService;
import com.changlu.vo.manage.MUserVo;
import com.changlu.vo.race.RaceVo;
import com.changlu.system.pojo.ZfRaceModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @ClassName ZfManageRaceServiceImpl
 * @Author ChangLu
 * @Date 4/7/2022 6:59 PM
 * @Description 管理竞赛业务实现层
 */
@Service
public class ZfManageRaceServiceImpl implements ZfManageRaceService {

    @Autowired
    private ZfRaceService zfRaceService;

    @Resource
    private ZfRaceMapper zfRaceMapper;

    @Resource
    private ZfMUserMapper zfMUserMapper;

    @Override
    public List<RaceVo> selectZfRaceModelList(ZfRaceModel raceModel) {
        //1、查询到所有竞赛集合
        Long[] raceIds = zfRaceMapper.selectZfRaceIds(); //  解决分页插件给我们查询记录总数问题，所以这里走一遍去查询对应页数的竞赛id
        List<RaceVo> raceVos = new ArrayList<>(raceIds.length);
        if (!ObjectUtils.isEmpty(raceIds)) {
            raceVos = zfRaceMapper.selectZfRaceModelListByRaceIds(raceModel, raceIds);
        }
        //2、查询出所有的用户记录（id、真实姓名）
        List<MUserVo> mUserVos = zfMUserMapper.selectSysUserIdAndRealName();
        Map<Long, String> userMap = mUserVos.stream().collect(Collectors.toMap(MUserVo::getUserId, MUserVo::getRealName));
        //3、遍历所有竞赛，根据对应member_ids来进行合成对应的姓名
        raceVos.stream().forEach(raceVo -> {
            String[] memIds = raceVo.getRaceMembers().split(",");
            StringBuilder teamMemberNames = new StringBuilder("");
            for (int i = 0; i < memIds.length; i++) {
                Long id = Long.valueOf(memIds[i]);
                teamMemberNames.append(userMap.get(id));
                if (i != memIds.length -1 ){
                    teamMemberNames.append(",");
                }
            }
            //生成个人or团队的用户名单
            raceVo.setTeamMemberNames(teamMemberNames.toString());
        });
        return raceVos;
    }
}
