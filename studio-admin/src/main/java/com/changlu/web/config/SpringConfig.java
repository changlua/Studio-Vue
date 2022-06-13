package com.changlu.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

/**
 * @ClassName SpringConfig
 * @Author ChangLu
 * @Date 5/6/2022 5:21 PM
 * @Description Spring的配置类
 */
@Configuration
public class SpringConfig {

    /**
     * 解决@Value()根据配置文件注入失败抛出异常情况
     */
    @Bean
    public PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer(){
        PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
        configurer.setIgnoreUnresolvablePlaceholders(true);
        return configurer;
    }

}
