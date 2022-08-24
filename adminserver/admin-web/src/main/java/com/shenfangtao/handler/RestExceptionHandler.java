package com.shenfangtao.handler;

import com.shenfangtao.model.ErrorCode;
import com.shenfangtao.model.ResultFormat;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Notes: 获取全局异常信息
 * Author: 涛声依旧 likeboat@163.com
 * Time: 2022/7/26 10:16
 */
@Slf4j
@RestControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResultFormat<String> exception(Exception e) {
        log.error("全局异常信息 ex={}", e.getMessage(), e);
        return ResultFormat.fail(ErrorCode.CODE_500.getCode(),e.getMessage());
    }
}
