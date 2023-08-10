package com.sbvadmin.model;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.util.List;

/**
 * Notes: 树形结构的基础模型类
 * Author: 涛声依旧 likeboat@163.com
 * Time: 2023/8/10 11:10
 */
@Data
public class TreeModel extends BaseModel {
    /**
     * ID
     */
    private Long id;

    /**
     * 上级机构ID，一级机构为0
     */
    private Long pid;

    /**
     * 排序
     */
    private Integer orderNo;

    /*
     * 子部门，形成tree结构
     **/
    @TableField(exist = false)
    private List children;

}
