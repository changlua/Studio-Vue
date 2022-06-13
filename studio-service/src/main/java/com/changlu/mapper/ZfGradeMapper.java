package com.changlu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.changlu.system.pojo.ZfGradeModel;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ChangLu
 * @since 2022-03-30
 */
public interface ZfGradeMapper extends BaseMapper<ZfGradeModel> {

    /**
     * 查询ZfGrade列表
     *
     * @param zfGradeModel ZfGrade
     * @return ZfGrade集合
     */
    public List<ZfGradeModel> selectZfGradeModelList(ZfGradeModel zfGradeModel);

    /**
     * 查询ZfGrade
     *
     * @param gradeId ZfGrade主键
     * @return ZfGrade
     */
    public ZfGradeModel selectZfGradeModelByGradeId(Long gradeId);

    /**
     * 新增ZfGrade
     *
     * @param zfGradeModel ZfGrade
     * @return 结果
     */
    public int insertZfGradeModel(ZfGradeModel zfGradeModel);

    /**
     * 修改ZfGrade
     *
     * @param zfGradeModel ZfGrade
     * @return 结果
     */
    public int updateZfGradeModel(ZfGradeModel zfGradeModel);

    /**
     * 批量删除ZfGrade
     *
     * @param gradeIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteZfGradeModelByGradeIds(Long[] gradeIds);

}
