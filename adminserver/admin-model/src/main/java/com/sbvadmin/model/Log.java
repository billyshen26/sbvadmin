package com.sbvadmin.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author billy
 * @since 2022-11-08
 */
@Getter
@Setter
//@Builder
@TableName("sys_log")
public class Log implements Serializable {
    public static final Byte ACTION_LEVEL = 1;
    public static final Byte ERROR_LEVEL = 2;
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 操作用户id
     */
    private Long uid;

    /**
     * 用户名
     */
    private String username;

    /**
     * 日志等级:1为行为日志,2为错误日志
     */
    private Byte level;

    /**
     * 操作描述
     */
    private String description;

    /**
     * 请求参数
     */
    private String reqParam;

    /**
     * 耗时
     */
    private Integer takeUpTime;

    /**
     * 操作方法
     */
    private String method;

    /**
     * 请求url
     */
    private String uri;

    /**
     * 请求IP
     */
    private String ip;

    /**
     * 请求地址
     */
    private String address;

    /**
     * 版本号
     */
    private String version;
//    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;
//    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updatedAt;
}
