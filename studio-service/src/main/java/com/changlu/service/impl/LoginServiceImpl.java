package com.changlu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.changlu.common.config.Constants;
import com.changlu.common.domain.LoginBody;
import com.changlu.common.domain.ResponseResult;
import com.changlu.common.exception.ServiceException;
import com.changlu.common.utils.JwtUtil;
import com.changlu.common.utils.MapUtil;
import com.changlu.common.utils.RedisCache;
import com.changlu.common.utils.RsaUtil;
import com.changlu.common.utils.uuid.IdUtils;
import com.changlu.domain.LoginUser;
import com.changlu.enums.ZfRoleEnum;
import com.changlu.security.service.TokenService;
import com.changlu.security.util.SecurityUtils;
import com.changlu.service.LoginService;
import com.changlu.system.mapper.SysUserMapper;
import com.changlu.system.mapper.SysUserRoleMapper;
import com.changlu.system.pojo.SysUser;
import com.changlu.system.pojo.SysUserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName LoginServiceImpl
 * @Author ChangLu
 * @Date 3/25/2022 2:50 PM
 * @Description 登录业务处理器
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Resource
    private SysUserMapper sysUserMapper;

    @Resource
    private SysUserRoleMapper sysUserRoleMapper;

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public Map<String,String> doLogin(LoginBody loginBody) {
        //1、校验验证码
        verifyCaptcha(loginBody.getCode(),loginBody.getUuid());
        //2、非对称解密密码
        try {
            loginBody.setPassword(RsaUtil.decryptByPrivateKey(loginBody.getPassword()));
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException("请不要尝试篡改加密密码！");
        }
        //3、进行用户认证
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginBody.getUsername(), loginBody.getPassword());
        Authentication authenticate = null;
        try {
            authenticate = authenticationManager.authenticate(authenticationToken);
        }catch (Exception e){  //注意在这里可以捕捉到BadCredentialsException异常
            if (e instanceof BadCredentialsException){
                throw new ServiceException("您的密码有误，请重新输入");  //抛出密码错误异常
            }else{
                throw new ServiceException(e.getMessage());
            }
        }
        // 若是认证没有通过，抛出异常
        if (ObjectUtils.isEmpty(authenticate)) {
            throw new ServiceException("用户认证失败");
        }
        // 认证通过，将用户信息取出存储到redis（默认存储时间）
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        //以前使用的是id来作为标识，应该使用一个uuid，并存储到用户对象中
//        String userId = loginUser.getUser().getUserId().toString();
        String uuid = IdUtils.simpleUUID();
        loginUser.setToken(uuid);
        tokenService.refreshToken(loginUser);
        //4、根据UserId来生成Token进行返回
        String token = JwtUtil.createJWT(uuid);
        Map<String,String> result = new HashMap<String,String>(1);
        result.put("token",token);
        return result;
    }

    @Override
    public void verifyCaptcha(String code, String uuid) {
        //redis获取验证码后删除
        String redisCode = redisCache.getCacheObject(Constants.CAPTCHA_CODE_KEY + uuid);
        redisCache.deleteObject(Constants.CAPTCHA_CODE_KEY + uuid);
        //情况一：验证码已失效
        if (ObjectUtils.isEmpty(redisCode)){
            throw new ServiceException("验证码已过期，已失效！");
        }
        //情况二：验证码有误
        if (!redisCode.equalsIgnoreCase(code)) {
            throw new ServiceException("验证码有误，请重新输入！");
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean registerUser(LoginBody loginBody) {
        //对密码进行非对称解密
        try {
            loginBody.setPassword(RsaUtil.decryptByPrivateKey(loginBody.getPassword()));
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException("请不要尝试篡改加密密码！");
        }
        //1、查询用户名是否存在
        LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<SysUser>()
                .eq(SysUser::getUserName, loginBody.getUsername());    // WHERE (user_name = xx);
        if (sysUserMapper.selectCount(queryWrapper) > 0){
            throw new ServiceException("用户名已存在，请重新输入！");
        }
        //2、注册用户
        String username = loginBody.getUsername();
        String password = loginBody.getPassword();
        String bcryptPasswd = SecurityUtils.bCryptPasswordEncoder.encode(password);
        SysUser registerUser = new SysUser(username, username, "{bcrypt}" + bcryptPasswd);
        if (sysUserMapper.insertUser(registerUser) > 0){
            //3、获取到刚刚自增的id
            Long userId = sysUserMapper.getLastInsertId();
            //4、分配用户角色（默认注册时是普通成员）
            List<SysUserRole> userRoles = new ArrayList<>(1);
            userRoles.add(new SysUserRole(userId, ZfRoleEnum.ROLE_MEMBER.value()));
            return sysUserRoleMapper.batchUserRole(userRoles) > 0;
        }
        throw new ServiceException("注册失败！");
    }


}
