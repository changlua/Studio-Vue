package com.changlu.enums;

/**
 * @ClassName ResoucesEnum
 * @Author ChangLu
 * @Date 4/4/2022 3:41 PM
 * @Description 资源表枚举类型
 */
public enum ZfResourceEnum {

    //竞赛表标识1
    RACE_FLAG("竞赛表zf_race", 1);

    private String name;
    private final int value;

    ZfResourceEnum(String name, int val){
        this.name = name;
        this.value = val;
    }

    public int value(){
        return this.value;
    }

}
