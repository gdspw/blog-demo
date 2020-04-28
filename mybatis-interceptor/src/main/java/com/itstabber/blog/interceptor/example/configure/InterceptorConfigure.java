/*
 * Copyright (C) 2020 Baidu, Inc. All Rights Reserved.
 */
package com.itstabber.blog.interceptor.example.configure;

import com.github.pagehelper.PageInterceptor;
import com.itstabber.blog.interceptor.example.interceptor.Interceptor1;
import com.itstabber.blog.interceptor.example.interceptor.Interceptor2;
import com.itstabber.blog.interceptor.example.interceptor.Interceptor3;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * TODO: 请添加描述
 *
 * @author Stabber
 * @date 2020/3/25 23:02
 * @since 1.0.0
 */
@Configuration
public class InterceptorConfigure {



    @Bean
    public Interceptor1 getInterceptor1(){
        return  new Interceptor1();
    }

    @Bean
    public Interceptor2 getInterceptor2(){
        return  new Interceptor2();
    }

    @Bean
    public PageInterceptor getPageInterceptor(){
        Properties properties=new Properties();
        properties.setProperty("helperDialect","mysql");
        properties.setProperty("reasonable","true");
        properties.setProperty("supportMethodsArguments","true");
        properties.setProperty("params","count=countSql");
        PageInterceptor pageInterceptor = new PageInterceptor();
        pageInterceptor.setProperties(properties);
        return pageInterceptor;
    }

    @Bean
    public Interceptor3 getInterceptor3(){
        return  new Interceptor3();
    }
}
