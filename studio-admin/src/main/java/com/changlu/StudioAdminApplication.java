package com.changlu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@SpringBootApplication
@MapperScan(basePackages = {"com.changlu.system.mapper","com.changlu.mapper"})
@EnableScheduling
@EnableGlobalMethodSecurity(prePostEnabled = true)   //开启授权
public class StudioAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudioAdminApplication.class, args);
    }

}
