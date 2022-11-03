package com.shenfangtao.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;

import java.math.BigInteger;
import java.time.LocalDateTime;

/**
 * Notes:
 * Author: 涛声依旧 likeboat@163.com
 * Time: 2022/8/2 14:41
 */
@Data
@Builder
public class Log {
    public static final int ACTION_LEVEL = 1;
    public static final int ERROR_LEVEL = 2;

    @TableId(type = IdType.AUTO)
    private Long id;
    private Long uid;
    private String username;
    private Integer level;
    private String description;
    private String reqParam;
    private Long takeUpTime;
    private String method;
    private String uri;
    private String ip;
    private String version;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createdAt;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime updatedAt;
}