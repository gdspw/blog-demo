/*
 * Copyright (C) 2020 Baidu, Inc. All Rights Reserved.
 */
package com.itstabber.blog.example.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * TODO: 请添加描述
 *
 * @author Stabber
 * @date 2020/3/24 01:15
 * @since 1.0.0
 */
@Data
@ApiModel("商品申请信息")
public class ProductRequest {

    @NotBlank(message = "商品名称不能为空或者全部为空串")
    @ApiModelProperty(name = "商品名称")
    private String productName;
    @NotNull(message = "商品编码不能空")
    @ApiModelProperty(name = "商品编码")
    private String productCode;
}
