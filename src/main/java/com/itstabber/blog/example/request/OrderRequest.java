/*
 * Copyright (C) 2020 Baidu, Inc. All Rights Reserved.
 */
package com.itstabber.blog.example.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import java.util.List;

/**
 * TODO: 请添加描述
 *
 * @author Stabber
 * @date 2020/3/24 01:15
 * @since 1.0.0
 */
@Data
@ApiModel("订单申请信息")
public class OrderRequest extends BaseRequest{

    @ApiModelProperty(name="订单编号")
    @Length( max= 10,message = "订单编号最大10位")
    private String orderNo;

    @Valid
    @ApiModelProperty(name="商品列表信息")
    private List<ProductRequest> productRequestList;

}
