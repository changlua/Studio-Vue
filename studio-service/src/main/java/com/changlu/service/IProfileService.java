package com.changlu.service;

import com.changlu.common.domain.ResponseResult;
import com.changlu.vo.PasswordVo;
import com.changlu.vo.ProfileVo;
import org.springframework.web.multipart.MultipartFile;

/**
 * @ClassName ISysProfile
 * @Author ChangLu
 * @Date 4/2/2022 7:49 PM
 * @Description 个人主页业务层
 */
public interface IProfileService {


    /**
     * 更新主页信息
     * @param profileVo 主页vo
     * @return
     */
    int updateProfile(ProfileVo profileVo);

    /**
     * 修改密码
     * @param passwordVo 修改密码vo
     * @return
     */
    ResponseResult updatePasswd(PasswordVo passwordVo);

    /**
     * 更新头像
     * @param file 文件
     * @return
     */
    ResponseResult updateAvatar(MultipartFile file);
}
