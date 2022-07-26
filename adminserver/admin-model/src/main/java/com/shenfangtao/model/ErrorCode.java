package com.shenfangtao.model;

/**
 * Notes:
 * Author: 涛声依旧 likeboat@163.com
 * Time: 2022/7/22 17:35
 */
public enum ErrorCode {
    SUCCESS(20000,"ok"),
    CODE_500(500,"服务器内部错误"),
    LOGIN_FAILED(401,"未认证"),
    TOKEN_INVALID(20001,"token 过期");

    /**自定义状态码**/
    private final int code;
    /**自定义描述**/
    private final String message;

    ErrorCode(int code, String message){
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
