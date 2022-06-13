package com.changlu.utils.file;

import com.changlu.common.exception.ServiceCallException;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * @ClassName FileUpload
 * @Author ChangLu
 * @Date 5/6/2022 4:25 PM
 * @Description 文件接口
 */
public interface FileUpload {

    /**
     * 上传单个文件
     */
    Map<String,String> uploadFile(MultipartFile file)throws ServiceCallException;

    /**
     * 删除单个文件
     */
    void deleteFile(String filename);

}
