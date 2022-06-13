package com.changlu.common.config.file;

import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @ClassName AliyunOssConfig
 * @Author ChangLu
 * @Date 3/26/2022 4:22 PM
 * @Description 阿里云OOS配置
 */
@Component
// 指定配置文件中自定义属性前缀
@ConfigurationProperties(prefix = "aliyun.oss")
@Data
@ToString
public class AliyunOssConfig {

    private String endpoint;
    private String accessKeyId;
    private String accessKeySecret;
    private String bucketName;
    private String key;

}
