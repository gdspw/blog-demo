/*
 * Copyright (C) 2020 Baidu, Inc. All Rights Reserved.
 */
package com.itstabber.blog.example.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.util.Properties;

/**
 * TODO: 请添加描述
 *
 * @author Stabber
 * @date 2020/3/25 22:58
 * @since 1.0.0
 */
@Slf4j
@Intercepts({
        @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class})
})
public class Interceptor3 implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        log.info("Interceptor3执行intercept>>>>");
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {

        log.info("Interceptor3执行plugin>>>>");
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
