/*
 * Copyright (C) 2020 Baidu, Inc. All Rights Reserved.
 */
package com.itstabber.blog.example.configure;

import com.google.common.base.Predicates;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * TODO: 请添加描述
 *
 * @author Stabber
 * @date 2019/12/30 18:05
 * @since 1.0.0
 */
@Configuration
@EnableSwagger2
public class Swagger2Configure {

    @Bean
    public Docket createRestApi(){
    return new Docket(DocumentationType.SWAGGER_2)
        .pathMapping("/")
        .apiInfo(apiInfo())
        .select()
        .apis(RequestHandlerSelectors.basePackage("com.itstabber.blog.example.web"))
        .paths(Predicates.not(PathSelectors.regex("/error.*")))
        .paths(Predicates.not(PathSelectors.regex("/actuator.*")))
        .paths(PathSelectors.regex("/.*"))
        .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("博客文章源码项目web演示接口文档")
                .description("博客文章源码项目")
                .version("1.0")
                .build();
    }
}
