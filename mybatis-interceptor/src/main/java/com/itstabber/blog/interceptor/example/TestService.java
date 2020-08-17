/*
 * Copyright (C) 2020 Baidu, Inc. All Rights Reserved.
 */
package com.itstabber.blog.interceptor.example;

/**
 * TODO: 请添加描述
 *
 * @author Stabber
 * @date 2020/8/9 16:51
 * @since 1.0.0
 */
public interface TestService {
    default int aa() {
        return 1+1;
    };
}
