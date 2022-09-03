package com.shenfangtao.handler;

import com.shenfangtao.model.ErrorCode;
import com.shenfangtao.model.ResultFormat;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
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
    /**
     * Notes:  401 认证相关异常
     * @param: [e]
     * @return: com.shenfangtao.model.ResultFormat<java.lang.String>
     * Author: 涛声依旧 likeboat@163.com
     * Time: 2022/8/28 16:15
     **/
    @ExceptionHandler(BadCredentialsException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResultFormat<String> badCredentialsException(Exception e) {
        log.error("认证异常 ex={}", e.getMessage(), e);
        return ResultFormat.fail(ErrorCode.LOGIN_FAILED.getCode(),e.getMessage());
    }

    /**
     * Notes:  权限相关异常
     * @param: [e]
     * @return: com.shenfangtao.model.ResultFormat<java.lang.String>
     * Author: 涛声依旧 likeboat@163.com
     * Time: 2022/8/28 16:16
     **/
    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResultFormat<String> accessDeniedException(Exception e) {
        log.error("权限异常 ex={}", e.getMessage(), e);
        return ResultFormat.fail(ErrorCode.ACCESS_DENIED.getCode(),e.getMessage());
    }

    /**
     * Notes:  请求参数异常相关
     * @param: [e]
     * @return: com.shenfangtao.model.ResultFormat<java.lang.String>
     * Author: 涛声依旧 likeboat@163.com
     * Time: 2022/9/1 14:17
     **/
    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResultFormat<String> methodArgumentNotValidException(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        StringBuilder builder = new StringBuilder("校验失败:");
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            builder.append(fieldError.getField()).append(":").append(fieldError.getDefaultMessage()).append(",");
        }
        String message = builder.toString();
        log.error("参数异常 ex={}", message, e);
        return ResultFormat.fail(ErrorCode.ACCESS_DENIED.getCode(),message);
    }


    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResultFormat<String> exception(Exception e) {
        log.error("全局异常信息 ex={}", e.getMessage(), e);
        return ResultFormat.fail(ErrorCode.CODE_500.getCode(),e.getMessage());
    }

}
