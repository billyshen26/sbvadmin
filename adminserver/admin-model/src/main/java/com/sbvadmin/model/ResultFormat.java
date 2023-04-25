package com.sbvadmin.model;

import lombok.Data;

/**
 * Notes: 格式化输出
 * Author: 涛声依旧 likeboat@163.com
 * Time: 2022/7/22 16:35
 */
@Data
public class ResultFormat<T> {
    private int code;
    private String message;
    private T result;

    /**
     * Notes:  返回成功
     * @param: [data]
     * @return: com.sbvadmin.model.ResultFormat<T>
     * Author: 涛声依旧 likeboat@163.com
     * Time: 2023/3/9 10:31
     **/
    public static <T> ResultFormat<T> success(T data){
        ResultFormat<T> resultFormat = new ResultFormat<>();
        resultFormat.setCode(ErrorCode.SUCCESS.getCode());
        resultFormat.setMessage(ErrorCode.SUCCESS.getMessage());
        resultFormat.setResult(data);
        return resultFormat;
    }

    /**
     * Notes:  返回成功仅消息
     * @param: [message]
     * @return: com.sbvadmin.model.ResultFormat<T>
     * Author: 涛声依旧 likeboat@163.com
     * Time: 2023/4/25 17:08
     **/
    public static <T> ResultFormat<T> success(String message){
        ResultFormat<T> resultFormat = new ResultFormat<>();
        resultFormat.setCode(ErrorCode.SUCCESS.getCode());
        resultFormat.setMessage(message);
        return resultFormat;
    }
    /**
     * Notes:  返回失败，包含自定义的message
     * @param: [errorCode, message]
     * @return: com.sbvadmin.model.ResultFormat<T>
     * Author: 涛声依旧 likeboat@163.com
     * Time: 2023/3/9 10:31
     **/
    public static <T> ResultFormat<T> fail (ErrorCode errorCode, String message){
        ResultFormat<T> resultFormat = new ResultFormat<>();
        resultFormat.setCode(errorCode.getCode());
        resultFormat.setMessage(message);
        return resultFormat;
    }

    /**
     * Notes:  返回失败，使用错误码内的message
     * @param: [errorCode]
     * @return: com.sbvadmin.model.ResultFormat<T>
     * Author: 涛声依旧 likeboat@163.com
     * Time: 2023/3/9 10:32
     **/
    public static <T> ResultFormat<T> fail (ErrorCode errorCode){
        ResultFormat<T> resultFormat = new ResultFormat<>();
        resultFormat.setCode(errorCode.getCode());
        resultFormat.setMessage(errorCode.getMessage());
        return resultFormat;
    }
}
