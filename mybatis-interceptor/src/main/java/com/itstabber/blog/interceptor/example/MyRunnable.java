/*
 * Copyright (C) 2020 Baidu, Inc. All Rights Reserved.
 */
package com.itstabber.blog.interceptor.example;

import lombok.Getter;

import java.time.LocalDateTime;

/**
 * TODO: 请添加描述
 *
 * @author Stabber
 * @date 2020/8/10 17:04
 * @since 1.0.0
 */
@Getter
public class MyRunnable implements Runnable{
    String name = "";

    public MyRunnable(String name){
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println("name:"+name+"开始时间："+LocalDateTime.now().toString());
            this.proccessComman();;
        System.out.println("name:"+name+"结束时间时间："+LocalDateTime.now().toString());
    }

    private void proccessComman(){
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
