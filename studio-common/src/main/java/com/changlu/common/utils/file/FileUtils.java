package com.changlu.common.utils.file;
import com.changlu.common.config.RuoYiConfig;
import com.changlu.common.utils.DateUtils;
import com.changlu.common.utils.uuid.IdUtils;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 文件处理工具类
 *
 * @author ruoyi
 */
public class FileUtils
{

    /**
     * 写数据到文件中
     *
     * @param data 数据
     * @return 目标文件
     * @throws IOException IO异常
     */
    public static String writeImportBytes(byte[] data) throws IOException
    {
        return writeBytes(data, RuoYiConfig.getImportPath());
    }

    /**
     * 写数据到文件中
     *
     * @param data 数据
     * @param uploadDir 目标文件
     * @return 目标文件
     * @throws IOException IO异常
     */
    public static String writeBytes(byte[] data, String uploadDir) throws IOException
    {
        FileOutputStream fos = null;
        String pathName = "";
        try
        {
            String extension = getFileExtendName(data);
            pathName = DateUtils.datePath() + "/" + IdUtils.fastUUID() + "." + extension;
            File file = FileUploadUtils.getAbsoluteFile(uploadDir, pathName);
            fos = new FileOutputStream(file);
            fos.write(data);
        }
        finally
        {
            IOUtils.close(fos);
        }
        return FileUploadUtils.getPathFileName(uploadDir, pathName);
    }
    /**
     * 获取图像后缀
     *
     * @param photoByte 图像数据
     * @return 后缀名
     */
    public static String getFileExtendName(byte[] photoByte)
    {
        String strFileExtendName = "jpg";
        if ((photoByte[0] == 71) && (photoByte[1] == 73) && (photoByte[2] == 70) && (photoByte[3] == 56)
                && ((photoByte[4] == 55) || (photoByte[4] == 57)) && (photoByte[5] == 97))
        {
            strFileExtendName = "gif";
        }
        else if ((photoByte[6] == 74) && (photoByte[7] == 70) && (photoByte[8] == 73) && (photoByte[9] == 70))
        {
            strFileExtendName = "jpg";
        }
        else if ((photoByte[0] == 66) && (photoByte[1] == 77))
        {
            strFileExtendName = "bmp";
        }
        else if ((photoByte[1] == 80) && (photoByte[2] == 78) && (photoByte[3] == 71))
        {
            strFileExtendName = "png";
        }
        return strFileExtendName;
    }

    /**
     * 获取名称
     *
     * @param fileName 路径名称
     * @return 没有文件路径的名称
     */
    public static String getName(String fileName)
    {
        if (fileName == null)
        {
            return null;
        }
        int lastUnixPos = fileName.lastIndexOf('/');
        int lastWindowsPos = fileName.lastIndexOf('\\');
        int index = Math.max(lastUnixPos, lastWindowsPos);
        return fileName.substring(index + 1);
    }
}
