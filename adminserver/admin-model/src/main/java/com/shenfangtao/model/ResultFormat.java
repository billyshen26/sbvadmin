package com.shenfangtao.model;

import lombok.Data;

/**
 * Notes: 格式化输出
 * Author: 涛声依旧 likeboat@163.com
 * Time: 2022/7/22 16:35
 */
@Data
public class ResultFormat<T> {
    private int status;
    private String message;
    private T data;

    public static <T> ResultFormat<T> success(T data){
        ResultFormat<T> resultFormat = new ResultFormat<>();
        resultFormat.setStatus(ErrorCode.SUCCESS.getCode());
        resultFormat.setMessage(ErrorCode.SUCCESS.getMessage());
        resultFormat.setData(data);
        return resultFormat;
    }

    public static <T> ResultFormat<T> fail (int code, String message){
        ResultFormat<T> resultFormat = new ResultFormat<>();
        resultFormat.setStatus(code);
        resultFormat.setMessage(message);
        return resultFormat;
    }
}
