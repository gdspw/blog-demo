/*
 * Copyright (C) 2020 Baidu, Inc. All Rights Reserved.
 */
package com.itstabber.blog.interceptor.example.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

/**
 * TODO: 请添加描述
 *
 * @author Stabber
 * @date 2020/3/23 23:25
 * @since 1.0.0
 */
@Data
@NoArgsConstructor
public class BaseResponse<T> {
    public static final int SUCCESS = 1;
    public static final int ERROR = 0;


    private String requestId;

    /**
     * 1,成功 0，失败
     */
    private Integer status = SUCCESS;
    private String errmsg;
    private String errno;

    private T data;

    public BaseResponse(String requestId) {
        this.requestId = requestId;
    }

    public BaseResponse(String requestId, String errmsg) {
        this.requestId = requestId;
        this.errmsg = errmsg;
    }

    public BaseResponse(String requestId, String errmsg, T data) {
        this.requestId = requestId;
        this.errmsg = errmsg;
        this.data = data;
    }

    public BaseResponse<T> setSuccess() {
        this.status = SUCCESS;
        this.errno = "200";
        return this;
    }

    public BaseResponse<T> setError(String errno) {
        this.errno = StringUtils.defaultString(errno, "400");
        this.status = ERROR;
        return this;
    }

    public boolean success() {
        return this.status != null && this.status.intValue() == SUCCESS;
    }

    public static <T> BaseResponse<T> errorResponse(String errmsg) {
        return BaseResponse.errorResponse(null, null, errmsg);
    }


    public static <T> BaseResponse<T> errorResponse(String requestId, String errno, String errmsg) {
        BaseResponse<T> response = new BaseResponse<>(requestId, StringUtils.defaultString(errmsg, "请求失败"));
        response.setError(errno);
        return response;
    }

    public static <T> BaseResponse<T> errorResponse(String requestId, String errmsg) {
        return BaseResponse.errorResponse(requestId, null, errmsg);
    }

    public static <T> BaseResponse<T> errorResponse(String requestId, String errmsg, T data) {
        BaseResponse<T> response = BaseResponse.errorResponse(requestId, errmsg);
        response.setData(data);
        return response;
    }

    public static <T> BaseResponse<T> errorAuthResponse(String requestId) {
        return BaseResponse.errorResponse(requestId, "403", "权限验证不通过");
    }

    public static <T> BaseResponse<T> errorParamsResponse(String requestId, BindingResult result) {
        StringBuilder errmsg = new StringBuilder();
        for (FieldError fieldError : result.getFieldErrors()) {
            errmsg.append(fieldError.getField()).append(":").append(fieldError.getDefaultMessage()).append(";");
        }
        return BaseResponse.errorResponse(requestId, errmsg.toString());
    }


    public static <T> BaseResponse<T> successResponse(String requestId, T data) {
        return BaseResponse.successResponse(requestId, null, data);
    }

    public static <T> BaseResponse<T> successResponse( T data) {
        return BaseResponse.successResponse(null, null, data);
    }

    public static <T> BaseResponse<T> successResponse(String requestId, String errmsg, T data) {
        BaseResponse<T> response = new BaseResponse<>(requestId, StringUtils.defaultString(errmsg, "请求成功"));
        response.setSuccess().setData(data);
        return response;
    }
}
