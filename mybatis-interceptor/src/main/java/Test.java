/*
 * Copyright (C) 2020 Baidu, Inc. All Rights Reserved.
 */

import com.itstabber.blog.interceptor.example.MapKey;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * TODO: 请添加描述
 *
 * @author Stabber
 * @date 2020/4/30 22:34
 * @since 1.0.0
 */
public class Test {

    public static void main(String[] args) {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        System.out.println(loader);
        System.out.println(loader.getParent());
        System.out.println(loader.getParent().getParent());
    }
}
