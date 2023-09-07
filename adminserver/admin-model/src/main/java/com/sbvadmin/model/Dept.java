package com.sbvadmin.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 机构管理
 * </p>
 *
 * @author billy
 * @since 2023-03-02
 */
@Getter
@Setter
@TableName("sys_dept")
public class Dept extends TreeModel implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 上级机构ID，一级机构为0
     */
    private Long pid;

    /**
     * 机构名称
     */
    private String name;

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


//    /*
//     * Notes:  子部门，形成tree结构
//     * @param:
//     * @return:
//     * Author: 涛声依旧 likeboat@163.com
//     * Time: 2023/3/2 21:19
//     **/
//    @TableField(exist = false)
//    private List<Dept> children;
}
