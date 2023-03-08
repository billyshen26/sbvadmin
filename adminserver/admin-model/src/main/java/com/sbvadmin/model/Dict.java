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
     * 字典值
     */
    private String value;

    /**
     * 字典名
     */
    private String label;

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
    private LocalDateTime createdAt;

    /**
     * 修改时间
     */
    private LocalDateTime updatedAt;


}
