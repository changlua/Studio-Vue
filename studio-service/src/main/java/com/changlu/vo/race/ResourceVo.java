package com.changlu.vo.race;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName ResourceVo
 * @Author ChangLu
 * @Date 4/4/2022 3:00 PM
 * @Description 资源
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResourceVo implements Serializable {


    /**
     * 资源路径
     */
    private String resUrl;

    /**
     * 资源名称
     */
    private String resName;

}
