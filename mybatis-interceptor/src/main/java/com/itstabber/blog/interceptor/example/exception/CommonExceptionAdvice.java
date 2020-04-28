/*
 * Copyright (C) 2020 Baidu, Inc. All Rights Reserved.
 */
package com.itstabber.blog.interceptor.example.exception;

import com.itstabber.blog.interceptor.example.response.BaseResponse;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @description:全局异常处理
 *
 * @author Stabber
 * @date 2020/3/23 23:25
 * @since 1.0.0
 */
@ControllerAdvice
@ResponseBody
public class CommonExceptionAdvice {
    private static Logger logger = LoggerFactory.getLogger(CommonExceptionAdvice.class);
    @Autowired
    private MessageSource messageSource;



    /**
     * 400 - Bad Request
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public BaseResponse handleMissingServletRequestParameterException(MissingServletRequestParameterException e) {
        String msg = String.format("缺少请求参数 paramName:%s, paramType:%s", e.getParameterName(), e.getParameterType());
        logger.error(msg, e);
        return BaseResponse.errorResponse(msg);
    }

    /**
     * 400 - Bad Request
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public BaseResponse handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        logger.error("参数解析失败", e);
        return BaseResponse.errorResponse("could_not_read_json");
    }

    /**
     * 400 - Bad Request
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public BaseResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        logger.error("参数验证失败:{}", e.getMessage());
        BindingResult result = e.getBindingResult();
        List<FieldError> errorList = e.getBindingResult().getFieldErrors();
        List<String> errorMessages = errorList.stream().map(x->{
            String itemMessage= messageSource.getMessage(x.getDefaultMessage(), null, x.getDefaultMessage(), LocaleContextHolder.getLocale());
            return String.format("%s", itemMessage);
        }).collect(Collectors.toList());
        return BaseResponse.errorResponse(errorMessages.toString());
    }

    /**
     * 400 - Bad Request
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(BindException.class)
    public BaseResponse handleBindException(BindException e) {
        logger.error("参数绑定失败", e);
        BindingResult result = e.getBindingResult();
        List<FieldError> errorList = e.getFieldErrors();;
        List<String> errorMessages = errorList.stream().map(x->{
            String itemMessage= messageSource.getMessage(x.getDefaultMessage(), null, x.getDefaultMessage(), LocaleContextHolder.getLocale());
            return String.format("%s", itemMessage);
        }).collect(Collectors.toList());
        return BaseResponse.errorResponse(errorMessages.toString());

    }

    /**
     * 400 - Bad Request
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(ConstraintViolationException.class)
    public BaseResponse handleServiceException(ConstraintViolationException e) {
        logger.error("参数验证失败", e);
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        ConstraintViolation<?> violation = violations.iterator().next();
        String message = violation.getMessage();
        return BaseResponse.errorResponse("parameter:" + message);
    }

    /**
     * 400 - Bad Request
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(ValidationException.class)
    public BaseResponse handleValidationException(ValidationException e) {
        logger.error("参数验证失败", e);
        String message = e.getCause().getMessage();
        if(StringUtils.isEmpty(message)) {
            message = "参数验证失败";
        }
        return BaseResponse.errorResponse(message);
    }

    /**
     * 405 - Method Not Allowed
     */
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public BaseResponse handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        logger.error("不支持当前请求方法", e);
        return BaseResponse.errorResponse("request_method_not_supported");
    }

    /**
     * 415 - Unsupported Media Type
     */
    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public BaseResponse handleHttpMediaTypeNotSupportedException(Exception e) {
        logger.error("不支持当前媒体类型", e);
        return BaseResponse.errorResponse("content_type_not_supported");
    }

    /**
     * 500 - Internal Server Error
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(Exception.class)
    public BaseResponse handleException(Exception e) {
        e.printStackTrace();
        logger.error("通用异常", e);
        return BaseResponse.errorResponse("系统繁忙，请稍候重新尝试");
    }


    /**
     * 参数转换失败，
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public BaseResponse handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e){

        logger.error("传递参数异常, 类型转换失败 param name = ["  + e.getName() + "], value = [" +  e.getValue() + "]." , e);
        return BaseResponse.errorResponse("传递参数异常, 类型转换失败 param name = ["  + e.getName() + "], value = [" +  e.getValue() + "].");
    }

    /**
     * 操作数据库出现异常:名称重复，外键关联
     */
//    @ResponseStatus(HttpStatus.OK)
//    @ExceptionHandler(DataIntegrityViolationException.class)
//    public BaseResponse handleException(DataIntegrityViolationException e) {
//        logger.error("操作数据库出现异常:", e);
//        return BaseResponse.errorResponse("系统异常，请稍后重试");
//    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(IOException.class)
    public BaseResponse handleIOException(IOException e){
        logger.error("内部错误（IOException）");
        String itemMessage =
                messageSource.getMessage(e.getMessage(), null, e.getMessage(), LocaleContextHolder.getLocale());
        String errorCause = String.valueOf(e.getCause());
        if (StringUtils.isNotEmpty(errorCause) && !"null".equals(errorCause)) {
            return BaseResponse.errorResponse(errorCause, itemMessage);
        }
        return BaseResponse.errorResponse(itemMessage);
    }



}
