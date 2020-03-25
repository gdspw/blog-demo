/*
 * Copyright (C) 2019 Baidu, Inc. All Rights Reserved.
 */
package com.itstabber.blog.example.test;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * TODO: 请添加描述
 *
 * @author Stabber
 * @date 2019/12/31 10:38
 * @since 1.0.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Rollback(false)
@EnableTransactionManagement
@EnableAsync
public class BaseTest {
    @Test
    public void contextLoads() {
    }

}
