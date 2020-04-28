/*
 * Copyright (C) 2020 Baidu, Inc. All Rights Reserved.
 */
package com.itstabber.blog.interceptor.example.request;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * TODO: 请添加描述
 *
 * @author Stabber
 * @date 2020/3/24 00:33
 * @since 1.0.0
 */
@Data
public class BaseRequest {
    @NotNull(message = "参数i不能为空")
    private Integer i;
    @NotBlank(message="参数s不能为空")
    private String s;

    @Min(value =0,message = "最小值不能小于0")
    private int min;

    @Max(value=100,message = "最大值不能大约100")
    private int max;


}
