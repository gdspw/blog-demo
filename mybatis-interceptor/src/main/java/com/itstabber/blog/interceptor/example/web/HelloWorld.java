/*
 * Copyright (C) 2020 Baidu, Inc. All Rights Reserved.
 */
package com.itstabber.blog.interceptor.example.web;

import com.itstabber.blog.interceptor.example.request.BaseRequest;
import com.itstabber.blog.interceptor.example.request.OrderRequest;
import com.itstabber.blog.interceptor.example.response.BaseResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * TODO: 请添加描述
 *
 * @author Stabber
 * @date 2020/3/22 21:31
 * @since 1.0.0
 */
@RequestMapping("/hello")
@RestController
@Api(tags = "HelloWorld 入口")
public class HelloWorld {

    @GetMapping("/world")
    @ApiOperation(value = "helloWorld")
    public String helloWorld() {
        return "hello world!";
    }


    @PostMapping("/test/param")
    @ApiOperation(value = "testParam")
    public BaseResponse<String> testParam(
            @Valid @RequestBody BaseRequest baseRequest
    ) {
        return BaseResponse.successResponse("参数校验成功");
    }

    @ApiOperation(value = "testOrderParam")
    @PostMapping("/test/order")
    public BaseResponse<String> testOrderParam(
            @Validated @RequestBody OrderRequest baseRequest
    ) {
        return BaseResponse.successResponse("参数校验成功");
    }
}