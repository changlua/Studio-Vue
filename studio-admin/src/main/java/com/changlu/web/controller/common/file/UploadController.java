package com.changlu.web.controller.common.file;

import com.changlu.common.domain.ResponseResult;
import com.changlu.common.exception.ServiceException;
import com.changlu.common.exception.ServiceCallException;
import com.changlu.utils.file.FileUpload;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @ClassName UploadTestController
 * @Author ChangLu
 * @Date 5/6/2022 4:49 PM
 * @Description 通用上传工具控制器
 */
@RestController
@Slf4j
@RequestMapping("/api/zf/api")
public class UploadController {

    //在com.changlu.web.config.file.UploadConfiguration中根据配置项使用对应的上传工具类
    @Qualifier("fileUpload")
    @Autowired
    private FileUpload fileUploadUtil;

    /**
     * 上传图片
     * @param file
     * @return
     */
    @PostMapping("/upload")
    public ResponseResult uploadFile(@RequestParam("file") MultipartFile file){
        Map<String,String> visitUrl = null;
        try {
            visitUrl = fileUploadUtil.uploadFile(file);
        }catch (ServiceCallException e){
            throw new ServiceException("上传失败！",e);
        }
        return ResponseResult.success("上传成功！", visitUrl);
    }

    /**
     * 上传多张图片
     * @param files
     * @return
     */
    @PostMapping("/upload/files")
    public ResponseResult uploadFiles(@RequestParam("file")MultipartFile[] files){
        List<Map<String,String>> visitUrls = new ArrayList<>(files.length);
        try {
            for (MultipartFile file : files) {
                Map<String,String> visitUrl = fileUploadUtil.uploadFile(file);
                visitUrls.add(visitUrl);
            }
        }catch (ServiceCallException e){
            throw new ServiceException("上传失败！",e);
        }
        return ResponseResult.success("上传成功！", visitUrls);
    }

    /**
     * 删除图片
     * @param fileName 文件名称如：b8809d28-82ec-4b06-af5f-8d3d7a16107c.jpg
     * @return
     */
    @DeleteMapping("/deleFile/{fileName}")
    public ResponseResult deleteFile(@PathVariable("fileName")String fileName){
        //若是url地址，那么进行筛选
        if (!ObjectUtils.isEmpty(fileName)) {
            try {
                fileUploadUtil.deleteFile(fileName);
            } catch (ServiceCallException e) {
                throw new ServiceException("删除失败！",e);
            }
        }
        return ResponseResult.success("删除成功！", null);
    }

}
