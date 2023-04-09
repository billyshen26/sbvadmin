package com.sbvadmin.model;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 字典
 * </p>
 *
 * @author billy
 * @since 2023-03-02
 */
@Getter
@Setter
@TableName("sys_dict")
public class Dict implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 父级ID
     */
    private Long pid;

    /**
     * 字典类型
     */
    private String type;

    /**
     * 字典类型名
     */
    private String typeName;

    /**
     * 字典值
     */
    private String value;

    /**
     * 字典名
     */
    private String label;

    /**
     * 图标
     */
    private String icon;

    /**
     * 描述
     */
    private String description;

    /**
     * 排序
     */
    private Integer orderNo;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createdAt;

    /**
     * 修改时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updatedAt;


}
