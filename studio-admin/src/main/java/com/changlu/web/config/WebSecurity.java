package com.changlu.web.config;

import com.changlu.security.filter.JwtAuthenticationTokenFilter;
import com.changlu.security.handler.AuthenticationEntryPointHandler;
import com.changlu.security.handler.MyAccessDeniedHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

/**
 * @ClassName WebSecurity
 * @Author ChangLu
 * @Date 3/25/2022 11:00 AM
 * @Description 自定义security配置
 */
@Configuration
public class WebSecurity extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;//自定义认证数据源

    @Autowired
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;//自定义认证处理器

    @Autowired
    private AuthenticationEntryPointHandler authenticationEntryPointHandler;//处理未认证时执行处理器
    @Autowired
    private MyAccessDeniedHandler myAccessDeniedHandler;//处理未授权时执行处理器
    @Autowired
    private LogoutSuccessHandler logoutSuccessHandler;//处理注销成功执行器

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);//配置自定义认证数据源
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(HttpMethod.GET, "/api/captchaImage","/api/common/**","/profile/*").permitAll()  //放行验证码接口
                .antMatchers(HttpMethod.POST, "/api/login","/api/register").permitAll()  //放行登录接口
                .antMatchers(HttpMethod.OPTIONS, "/api/*").permitAll()  //放行预检接口
                .anyRequest().authenticated()
                .and()
                    .exceptionHandling()
                        .authenticationEntryPoint(authenticationEntryPointHandler)  //认证异常，默认返回403的HTTP请求
                        .accessDeniedHandler(myAccessDeniedHandler)  //授权异常，默认返回403的HTTP请求
                .and()
                    .logout().
                        logoutUrl("/api/logout").logoutSuccessHandler(logoutSuccessHandler)  //自定义注销成功处理器
                .and()
                    .csrf().disable()
                    //不通过Session获取SecurityContext（当前已经是前后端分离）
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        //添加JWT认证过滤器
        //注意点：此时logoutfilter也就是意味着不会走jwtfilter，那么logoutfilter就必须手动从header中取出token来删除会话信息了
        http.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);

        //支持跨域
        http.cors();
    }

    //让AuthenicationManager从工厂中暴露出来
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
       return super.authenticationManagerBean();
    }
}
