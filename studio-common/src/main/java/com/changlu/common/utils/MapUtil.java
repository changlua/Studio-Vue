package com.changlu.common.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName MapUtil
 * @Author ChangLu
 * @Date 3/28/2022 11:01 AM
 * @Description Map工具类
 */
public class MapUtil {

    /**
     * 创建key为string，value为object的HashMap对象
     * @param size 创建的容量大小
     * @return
     */
    public static Map<String,Object> createHashMap(int size){
        return new HashMap<String,Object>(size);
    }

}
