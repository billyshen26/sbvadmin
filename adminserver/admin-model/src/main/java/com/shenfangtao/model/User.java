package com.shenfangtao.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.math.BigInteger;
import java.util.Date;

/**
 * Notes: 用户模型
 * Author: 涛声依旧 likeboat@163.com
 * Time: 2022/7/12 20:07
 */
@Data
public class User {
    @TableId(type = IdType.AUTO)
    private BigInteger id;
    private String name;
    private String phone;
    private String email;
    private Boolean enabled;
    private String username;
    private String password;
    private String avatar;
    private Date last_login_at;
    private String last_login_ip;
    private Date created_at;
    private Date updated_at;
    private String mp_open_id;
    private String union_id;
//    private Boolean locked;

}
