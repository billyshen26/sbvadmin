package com.shenfangtao.model;

/**
 * Notes:
 * Author: 涛声依旧 likeboat@163.com
 * Time: 2022/7/22 17:35
 */
public enum ErrorCode {
    SUCCESS(0,"ok"),
    CODE_500(500,"服务器内部错误"),
    LOGIN_FAILED(401,"未认证"),
    TOKEN_INVALID(401,"token 过期"),
    ACCESS_DENIED(403, "权限不够"),
    BAD_REQUEST(400, "参数异常"),

    ROOT_CANT_DELETE(20001, "超级管理员不能被删除"),
    ROOT_CANT_UPDATE(20002, "超级管理员不能被修改");

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
