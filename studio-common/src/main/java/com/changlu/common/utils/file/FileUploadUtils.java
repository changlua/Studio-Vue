package com.changlu.common.utils.file;


import com.changlu.common.config.Constants;
import com.changlu.common.config.RuoYiConfig;
import com.changlu.common.utils.StringUtils;

import java.io.File;
import java.io.IOException;

/**
 * 文件上传工具类
 *
 * @author ruoyi
 */
public class FileUploadUtils
{

    public static final File getAbsoluteFile(String uploadDir, String fileName) throws IOException
    {
        File desc = new File(uploadDir + File.separator + fileName);

        if (!desc.exists())
        {
            if (!desc.getParentFile().exists())
            {
                desc.getParentFile().mkdirs();
            }
        }
        return desc;
    }

    public static final String getPathFileName(String uploadDir, String fileName) throws IOException
    {
        int dirLastIndex = RuoYiConfig.getProfile().length() + 1;
        String currentDir = StringUtils.substring(uploadDir, dirLastIndex);
        return Constants.RESOURCE_PREFIX + "/" + currentDir + "/" + fileName;
    }
}
