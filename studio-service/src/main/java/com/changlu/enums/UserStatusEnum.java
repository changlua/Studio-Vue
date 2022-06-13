package com.changlu.enums;

/**
 * @ClassName UserStatusEnum
 * @Author ChangLu
 * @Date 4/6/2022 2:37 PM
 * @Description 用户账号状态
 */
public enum UserStatusEnum {

    /**
     * ACTIVE: 账号正常
     * DISABLED：账号停用
     */
    ACTIVE("正常","0"),DISABLED("停用", "1");

    private String name;
    private String status;

    UserStatusEnum(String name, String status){
        this.name = name;
        this.status = status;
    }

    public String value() {
        return this.status;
    }
}
