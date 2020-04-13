/*
 * Copyright (C) 2020 Baidu, Inc. All Rights Reserved.
 */
package com.itstabber.blog.example.test.user;

import com.itstabber.blog.example.model.User;
import com.itstabber.blog.example.repository.UserMapper;
import com.itstabber.blog.example.test.BaseTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * TODO: 请添加描述
 *
 * @author Stabber
 * @date 2020/3/25 23:59
 * @since 1.0.0
 */
@Slf4j
public class UserQueryForInterceptorTest extends BaseTest {

    @Resource
    UserMapper userMapper;

    @Test
    public void testQuery(){
        User user = userMapper.selectByPrimaryKey(1L);
        log.info("user:{}",user.toString());
    }

    @Test
    public void testSave(){
        User user = User.builder()
                .userName("test")
                .userAccount("testAccount")
                .build();
        userMapper.insertSelective(user);
    }
}
