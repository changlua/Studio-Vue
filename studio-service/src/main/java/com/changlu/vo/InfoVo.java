package com.changlu.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName InfoVo
 * @Author ChangLu
 * @Date 3/31/2022 12:55 PM
 * @Description 用于接收Info层传来的对象
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InfoVo implements Serializable {

    /** 真实姓名 */
    private String realName;

    /** 个人描述 */
    private String description;

    /** 个人照片 */
    private String perImg;

    /** 专业id */
    private Long majorId;

    /** 年纪id */
    private Long gradeId;

}
