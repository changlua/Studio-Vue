package com.changlu.config;

import com.changlu.utils.file.AliyunOssUtil;
import com.changlu.utils.file.FileUpload;
import com.changlu.utils.file.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName UploadConfigure
 * @Author ChangLu
 * @Date 5/6/2022 4:36 PM
 * @Description 上传配置类
 */
@Configuration
public class UploadConfiguration {

    @Value("${uploadOption.method}")
    private String uploadMethod;

    /**
     * 默认配置
     */
    private static String DEFAULT_UPLOAD_METHOD = "file";

    //工厂中的上传工具类
    @Autowired
    private AliyunOssUtil aliyunOssUtil;

    @Autowired
    private FileUtil fileUtil;

    @Bean("fileUpload")
    public FileUpload fileUpload(){
        if (uploadMethod == null) {
            this.uploadMethod = DEFAULT_UPLOAD_METHOD;
        }
        //选择不同的方式来进行构建上传对象
        if ("file".equals(this.uploadMethod)) {
            return fileUtil;
        }else if ("aliyun".equals(this.uploadMethod)) {
            return aliyunOssUtil;
        }
        return null;
    }

}
