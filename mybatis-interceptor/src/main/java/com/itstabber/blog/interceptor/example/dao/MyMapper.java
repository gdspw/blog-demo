/*
 * Copyright (C) 2020 Baidu, Inc. All Rights Reserved.
 */
package com.itstabber.blog.interceptor.example.dao;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * TODO: 请添加描述
 *
 * @author Stabber
 * @date 2020/3/25 23:45
 * @since 1.0.0
 */
public interface MyMapper<T> extends Mapper<T>, MySqlMapper<T> {
}
