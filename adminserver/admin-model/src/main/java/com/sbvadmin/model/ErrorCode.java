package com.sbvadmin.model;

/**
 * Notes:
 * Author: 涛声依旧 likeboat@163.com
 * Time: 2022/7/22 17:35
 */
public enum ErrorCode {

    // 0 为成功
    SUCCESS(0,"ok"),

    // 200 - 600 参考http code
    CODE_500(500,"服务器内部错误"),
    LOGIN_FAILED(401,"未认证"),
    TOKEN_INVALID(401,"token 过期"),
    ACCESS_DENIED(403, "权限不够"),
    BAD_REQUEST(400, "参数异常"),

    // 20001-29999 框架自定错误
    FAILED(20001, "操作失败"),
    ROOT_CANT_DELETE(20002, "超级管理员不能被删除"),
    ROOT_CANT_UPDATE(20003, "超级管理员不能被修改"),
    PERMISSION_CANT_DELETE(20004, "预定权限点不能被删除"),
    RABBITMQ_NOT_ACTIVE(20005, "用户数据库添加成功,但Rabbit MQ 服务未启用");

    // 30001-39999 用户自定义错误


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
