package com.sbvadmin.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * Notes:
 * @param:
 * @return:
 * Author: 涛声依旧 likeboat@163.com
 * Time: 2022/9/18 20:03
 **/
@Data
@TableName("sys_role_permission")
public class RolePermission implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 角色外键
     */
    private Long rid;

    /**
     * 权限外键
     */
    private Long pid;
}
