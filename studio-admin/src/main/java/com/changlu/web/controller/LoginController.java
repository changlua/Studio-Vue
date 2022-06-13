package com.changlu.web.controller;

import com.changlu.common.config.Constants;
import com.changlu.common.domain.LoginBody;
import com.changlu.common.domain.ResponseResult;
import com.changlu.common.easycaptcha.enums.CodeTypeEnum;
import com.changlu.common.easycaptcha.service.EasyCaptchaService;
import com.changlu.common.utils.RedisCache;
import com.changlu.security.util.SecurityUtils;
import com.changlu.common.utils.uuid.IdUtils;
import com.changlu.service.LoginService;
import com.changlu.system.pojo.SysMenu;
import com.changlu.system.pojo.SysUser;
import com.changlu.system.service.ISysMenuService;
import com.changlu.system.service.impl.SysPermissionService;
import com.changlu.common.utils.MapUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName LoginController
 * @Author ChangLu
 * @Date 3/25/2022 2:44 PM
 * @Description 登录控制器
 */
@RestController
@RequestMapping("/api")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private EasyCaptchaService easyCaptchaService;

    //注册邀请码
    @Value("${zf.register.invitationCode}")
    public String invitationCode;

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private SysPermissionService permissionService;

    @Autowired
    private ISysMenuService menuService;

    @PostMapping("/register")
    public ResponseResult register(@RequestBody LoginBody loginBody){
        if (!ObjectUtils.isEmpty(loginBody) && !ObjectUtils.isEmpty(loginBody.getInvitationCode())
            && !invitationCode.equalsIgnoreCase(loginBody.getInvitationCode())){
            return ResponseResult.error("邀请码有误，请重新输入！");
        }
        return ResponseResult.toResponse(loginService.registerUser(loginBody));
    }

    /**
     * 登录接口
     * @param loginBody 登录请求体
     * @return
     */
    @PostMapping("/login")
    public ResponseResult login(@RequestBody LoginBody loginBody){
        return ResponseResult.success(loginService.doLogin(loginBody));
    }

    /**
     * 获取验证码
     * @return
     * @throws IOException
     */
    @GetMapping("/captchaImage")
    public ResponseResult captchaImage(){
        Map<String, String> captchaResult = easyCaptchaService.getCaptchaValueAndBase64(CodeTypeEnum.GIF);
        //1、生成验证码
        String code = captchaResult.get("code");
        //2、存储到redis中（借助一个uuid来进行判别）
        String uuid = IdUtils.simpleUUID();
        redisCache.setCacheObject(Constants.CAPTCHA_CODE_KEY + uuid, code, Constants.CAPTCHA_EXPIRATION, TimeUnit.MINUTES);
        //3、生成Base64编码返回
        String captchaBase64 = captchaResult.get("base64");
        //4、构造相应对象集
        Map<String,Object> result = MapUtil.createHashMap(2);
        result.put("img", captchaBase64);
        result.put("uuid", uuid);
        return ResponseResult.success("验证码",result);
    }


    @GetMapping("/getInfo")
    public ResponseResult getInfo(){
        SysUser user = SecurityUtils.getLoginUser().getUser();
        // 角色集合
        Set<String> roles = permissionService.getRolePermission(user);
        // 权限集合
        Set<String> permissions = permissionService.getMenuPermission(user);
        Map<String,Object> result = MapUtil.createHashMap(3);
        result.put("user", user);
        result.put("roles", roles);
        result.put("permissions", permissions);
        return ResponseResult.success(result);
    }

    /**
     * 获取路由信息
     *
     * @return 路由信息
     */
    @GetMapping("getRouters")
    public ResponseResult getRouters()
    {
        Long userId = SecurityUtils.getUserId();
        //获取菜单表中menu_type为M、C的菜单记录。取得的集合是已经进行父子表递归过的
        List<SysMenu> menus = menuService.selectMenuTreeByUserId(userId);
        //构建前端所需要的路由表
        return ResponseResult.success(menuService.buildMenus(menus));
    }

}
