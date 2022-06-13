package com.changlu.common.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 读取项目相关配置
 *
 * @author ruoyi
 */
@Component
@ConfigurationProperties(prefix = "ruoyi")
public class RuoYiConfig
{

    /** 上传路径 */
    private static String profile;

    /**
     * 获取导入上传路径
     */
    public static String getImportPath()
    {
        return getProfile() + "/import";
    }
    /**
     * 获取下载路径
     */
    public static String getDownloadPath()
    {
        return getProfile() + "/download/";
    }

    public static String getProfile()
    {
        return profile;
    }

    public void setProfile(String profile)
    {
        RuoYiConfig.profile = profile;
    }
}
