/*
 * Copyright (C) 2020 Baidu, Inc. All Rights Reserved.
 */
package com.itstabber.blog.example.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
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
public class Interceptor1 implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object[] args = invocation.getArgs();
        MappedStatement ms = (MappedStatement) args[0];
        Object parameter = args[1];
        RowBounds rowBounds = (RowBounds) args[2];
        Executor executor = (Executor) invocation.getTarget();
        BoundSql boundSql;
        boundSql = ms.getBoundSql(parameter);
        executor.createCacheKey(ms, parameter, rowBounds, boundSql);
        log.info("Interceptor1执行intercept>>>>");
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        log.info("Interceptor1执行plugin>>>>");
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
