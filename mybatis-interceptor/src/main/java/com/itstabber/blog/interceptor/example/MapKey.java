/*
 * Copyright (C) 2020 Baidu, Inc. All Rights Reserved.
 */
package com.itstabber.blog.interceptor.example;

/**
 * TODO: 请添加描述
 *
 * @author Stabber
 * @date 2020/8/12 16:20
 * @since 1.0.0
 */
public class MapKey {
    private Integer i;

    public MapKey(Integer i){
        this.i =i;
    }

    public int hashCode(){
        return i.hashCode();
    }
}
