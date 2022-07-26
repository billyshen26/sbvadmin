package com.shenfangtao.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

/**
 * Notes:
 * Author: 涛声依旧 likeboat@163.com
 * Time: 2022/6/13 22:28
 */
@Data
public class Role implements Serializable {
    @TableId(type = IdType.AUTO)
    private BigInteger id;
    private String name;
    private String alias;
    private String description;
    private Date created_at;
    private Date updated_at;
}
