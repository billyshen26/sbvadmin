package com.sbvadmin.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sbvadmin.model.ResultFormat;
import lombok.SneakyThrows;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.annotation.Resource;

/**
 * Notes: 格式化返回结果
 * Author: 涛声依旧 likeboat@163.com
 * Time: 2022/7/25 10:58
 */
@RestControllerAdvice
public class CustomResponseBodyAdvice implements ResponseBodyAdvice<Object> {
    @Resource
    private ObjectMapper objectMapper;

    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        return true;
    }

    @SneakyThrows
    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        if(o instanceof String){ // 如果返回结果是字符串则要转化下
            return objectMapper.writeValueAsString(ResultFormat.success(o));
        }
        if(o instanceof ResultFormat){ // 解决异常处理和自定义处理
            return o;
        }
        return ResultFormat.success(o); // 直接返回
    }
}
