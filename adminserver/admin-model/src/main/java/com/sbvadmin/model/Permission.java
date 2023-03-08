package com.sbvadmin.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Notes:
 * Author: 涛声依旧 likeboat@163.com
 * Time: 2022/7/15 11:09
 */
@Data
@TableName("sys_permission")
public class Permission implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Long pid;

    /**
     * 后端请求路径
     */
    @NotNull(message = "后端请求路径不能为空")
    private String requestUrl;

    /**
     * 后端请求方式
     */
    @NotNull(message = "后端请求方式不能为空")
    private String requestMethod;

    /**
     * 菜单名称
     */
    @NotNull(message = "菜单名称不能为空")
    private String name;

    /**
     * 菜单名称i18n
     */
    private String title;

    /**
     * 描述
     */
    private String description;

    /**
     * 前端页面路径
     */
    private String path;

    /**
     * 前端页面组件
     */
    private String component;

    /**
     * 图标
     */
    private String icon;

    /**
     * 是否显示:0-隐藏;1-显示
     */
    private Byte showFlag;

    /**
     * 权限类型:0-目录;1-菜单;2-按钮
     */
    private Byte type;

    /**
     * 状态:0-禁用;1-启用
     */
    private Byte status;

    /**
     * 排序
     */
    private Byte orderNo;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
    @TableField(exist = false)
    private List<Role> roles;
}
