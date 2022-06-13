package com.changlu.web.config;

import com.changlu.common.config.file.LocalUploadConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    private LocalUploadConfig localUploadConfig;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //映射上传资源的路径 如:/images/**  => file:D:/changlu/闲暇目录/upload/
        //注意：在指定的文件目录前一定要加file:表示使用文件访问协议
        registry.addResourceHandler( localUploadConfig.visitPath+"/**").addResourceLocations("file:" + localUploadConfig.FILE_UPLOAD_DIR);
    }
}
