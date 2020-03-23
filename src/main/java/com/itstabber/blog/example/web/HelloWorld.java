/*
 * Copyright (C) 2020 Baidu, Inc. All Rights Reserved.
 */
package com.itstabber.blog.example.web;

import com.itstabber.blog.example.request.BaseRequest;
import com.itstabber.blog.example.response.BaseResponse;
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
public class HelloWorld {

    @GetMapping("/world")
    public String helloWorld(){
        return "hello world!";
    }


    @PostMapping("/test/param")
    public BaseResponse<String> testParam(
           @Valid @RequestBody BaseRequest baseRequest
            ){
        return BaseResponse.successResponse("参数校验成功");
    }
}
