package com.changlu.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName IndexCountMapper
 * @Author ChangLu
 * @Date 4/9/2022 12:32 PM
 * @Description 首页统计mapper
 */
public interface IndexCountMapper {

    List<Long> countRacesByGrade(@Param("beginYear") Integer beginYear,@Param("endYear") Integer endYear);

    List<Long> countCciesByGrade(@Param("beginYear") Integer beginYear,@Param("endYear") Integer endYear);

    List<Long> countRacesByMajor();

    List<Long> countCciesByMajor();

}
