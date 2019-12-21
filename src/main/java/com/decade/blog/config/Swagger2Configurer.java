package com.decade.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author decade
 * @create 2019-11-23 19:52
 */
@Configuration
@EnableSwagger2
public class Swagger2Configurer {

    @Bean
    public Docket webApiConfig(){
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("webApi")
                .apiInfo(webApiInfo())
                .select()
                .build();
    }

    private ApiInfo webApiInfo(){
        return new ApiInfoBuilder()
                .title("博客API文档")
                .description("本文档描述了博客管理微服务接口定义")
                .version("1.0")
                .contact(new Contact("decade", "http://decade.com", "123456789@qq.com"))
                .build();
    }

}
