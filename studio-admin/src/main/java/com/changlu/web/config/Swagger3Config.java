package com.changlu.web.config;

import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @ClassName Swagger3Config
 * @Author ChangLu
 * @Date 2021/9/20 20:55
 * @Description swagger3配置类
 */
@Configuration
@EnableOpenApi  //开启swagger3
public class Swagger3Config {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo())
                .select()
                //扫描的路径包,这里扫描所有带有@ApiOperation注解的方法
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                //指定路径处理PathSelectors.any()代表所有的路径
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                //设置文档标题(API名称)
                .title("工作室后台管理系统")
                //文档描述
                .description("工作室后台管理系统web接口文档说明")
                // 作者信息：作者名称、官网、邮箱
                .contact(new Contact("changlu", "http://localhost:8080", "939974883@qq.com"))
                //文档版本
                .version("1.0")
                .build();
    }
}
