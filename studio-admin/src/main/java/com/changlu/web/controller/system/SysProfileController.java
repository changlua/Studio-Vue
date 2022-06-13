package com.changlu.web.controller.system;

import com.changlu.common.domain.ResponseResult;
import com.changlu.security.util.SecurityUtils;
import com.changlu.service.IProfileService;
import com.changlu.service.IUserService;
import com.changlu.system.pojo.SysUser;
import com.changlu.vo.PasswordVo;
import com.changlu.vo.ProfileVo;
import com.changlu.common.utils.MapUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * @ClassName SysProfileController
 * @Author ChangLu
 * @Date 3/28/2022 10:26 PM
 * @Description 系统用户控制器
 */
@RestController
@RequestMapping("/api/system/user")
public class SysProfileController {

    @Autowired
    private IUserService userService;

    @Autowired
    private IProfileService profileService;

    @GetMapping("/profile")
    @PreAuthorize("@ss.hasRole('member')")
    public ResponseResult profile(){
        SysUser user = SecurityUtils.getUser();
        //使用,分割开来的
        String roleGroup = userService.selectUserRoleGroup(user);
        Map result = MapUtil.createHashMap(2);
        result.put("user", user);
        result.put("roleGroup", roleGroup);
        return ResponseResult.success(result);
    }

    @PostMapping("/profile")
    @PreAuthorize("@ss.hasRole('member')")
    public ResponseResult updateProfile(@RequestBody ProfileVo profileVo){
        int result = profileService.updateProfile(profileVo);
        if (result > 0) {
            return ResponseResult.success("更新成功！",null);
        }
        return ResponseResult.error("更新失败！");
    }

    @PostMapping("/updatePwd")
    @PreAuthorize("@ss.hasRole('member')")
    public ResponseResult updatePwd(@RequestBody PasswordVo passwordVo){
        return profileService.updatePasswd(passwordVo);
    }

    @PostMapping("/profile/avatar")
    @PreAuthorize("@ss.hasRole('member')")
    public ResponseResult updateAvatar(@RequestParam("avatarfile")MultipartFile file){
        return profileService.updateAvatar(file);
    }

}
