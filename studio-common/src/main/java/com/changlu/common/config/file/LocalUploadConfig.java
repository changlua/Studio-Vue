package com.changlu.common.config.file;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class LocalUploadConfig {

    @Value("${file.visit.path}")
    public String visitPath;   //资源访问路径

    @Value("${file.upload.dir}")
    public String FILE_UPLOAD_DIR;   //图片上传磁盘位置

    @Value("${file.visit.ip}")
    public String ip;  //上传ip地址

    @Value("${server.port}")
    public String port;  //服务器端口号

    @Value("${file.visit.protocol}")
    public String protocol;//请求协议

}
