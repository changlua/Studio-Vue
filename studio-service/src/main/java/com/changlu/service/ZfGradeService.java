package com.changlu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.changlu.common.domain.ResponseResult;
import com.changlu.system.pojo.ZfGradeModel;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ChangLu
 * @since 2022-03-30
 */
public interface ZfGradeService extends IService<ZfGradeModel> {

    /**
     * 查询ZfGrade列表
     *
     * @param zfGradeModel ZfGrade
     * @return ZfGrade集合
     */
    List<ZfGradeModel> selectZfGradeModelList(ZfGradeModel zfGradeModel);

    /**
     * 查询ZfGrade
     *
     * @param gradeId ZfGrade主键
     * @return ZfGrade
     */
    ZfGradeModel selectZfGradeModelByGradeId(Long gradeId);

    /**
     * 新增ZfGrade
     *
     * @param zfGradeModel ZfGrade
     * @return 结果
     */
    int insertZfGradeModel(ZfGradeModel zfGradeModel);


    /**
     * 修改ZfGrade
     *
     * @param zfGradeModel ZfGrade
     * @return 结果
     */
    int updateZfGradeModel(ZfGradeModel zfGradeModel);

    /**
     * 批量删除ZfGrade
     *
     * @param gradeIds 需要删除的ZfGrade主键集合
     * @return 结果
     */
    int deleteZfGradeModelByGradeIds(Long[] gradeIds);


}
