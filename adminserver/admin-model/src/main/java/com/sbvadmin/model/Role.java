package com.sbvadmin.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Notes:
 * Author: 涛声依旧 likeboat@163.com
 * Time: 2022/6/13 22:28
 */
@Data
@TableName("sys_role")
public class Role extends BaseModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 英文名称
     */
    @NotNull(message = "角色值不能为空")
    private String name;

    /**
     * 中文名称
     */
    @NotNull(message = "角色名不能为空")
    private String nameZh;

    /**
     * 描述
     */
    private String description;

    /**
     * 状态:0-禁用;1-启用
     */
    private Boolean status;

    /**
     * 排序
     */
    private Integer orderNo;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @TableField(exist = false)
    private List<Long> menu;
}
