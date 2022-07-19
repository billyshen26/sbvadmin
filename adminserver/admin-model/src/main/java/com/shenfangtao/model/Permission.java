package com.shenfangtao.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

/**
 * Notes:
 * Author: 涛声依旧 likeboat@163.com
 * Time: 2022/7/15 11:09
 */
@Data
public class Permission {
    @TableId(type = IdType.AUTO)
    private BigInteger id;
    private BigInteger pid;
    private String pattern;
    private String method;
    private String alias;
    private String description;
    private Date created_at;
    private Date updated_at;
    @TableField(exist = false)
    private List<Role> roles;
}
