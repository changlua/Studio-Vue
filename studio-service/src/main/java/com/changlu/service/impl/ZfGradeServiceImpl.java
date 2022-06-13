package com.changlu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.changlu.common.domain.ResponseResult;
import com.changlu.mapper.ZfGradeMapper;
import com.changlu.service.ZfGradeService;
import com.changlu.system.pojo.ZfGradeModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ChangLu
 * @since 2022-03-30
 */
@Service
public class ZfGradeServiceImpl extends ServiceImpl<ZfGradeMapper, ZfGradeModel> implements ZfGradeService {

    @Autowired
    private ZfGradeMapper gradeMapper;


    /**
     * 查询ZfGrade列表
     *
     * @param zfGradeModel ZfGrade
     * @return ZfGrade
     */
    @Override
    public List<ZfGradeModel> selectZfGradeModelList(ZfGradeModel zfGradeModel)
    {
        return gradeMapper.selectZfGradeModelList(zfGradeModel);
    }

    /**
     * 查询ZfGrade
     *
     * @param gradeId ZfGrade主键
     * @return ZfGrade
     */
    @Override
    public ZfGradeModel selectZfGradeModelByGradeId(Long gradeId)
    {
        return gradeMapper.selectZfGradeModelByGradeId(gradeId);
    }

    /**
     * 新增ZfGrade
     *
     * @param zfGradeModel ZfGrade
     * @return 结果
     */
    @Override
    public int insertZfGradeModel(ZfGradeModel zfGradeModel)
    {
        return gradeMapper.insertZfGradeModel(zfGradeModel);
    }

    /**
     * 修改ZfGrade
     *
     * @param zfGradeModel ZfGrade
     * @return 结果
     */
    @Override
    public int updateZfGradeModel(ZfGradeModel zfGradeModel)
    {
        return gradeMapper.updateZfGradeModel(zfGradeModel);
    }

    /**
     * 批量删除ZfGrade
     *
     * @param gradeIds 需要删除的ZfGrade主键
     * @return 结果
     */
    @Override
    public int deleteZfGradeModelByGradeIds(Long[] gradeIds)
    {
        return gradeMapper.deleteZfGradeModelByGradeIds(gradeIds);
    }

}
