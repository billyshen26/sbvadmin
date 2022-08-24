package com.shenfangtao.model;

import lombok.Data;

import java.math.BigInteger;
import java.util.List;

/**
 * Notes: 带token的用户信息
 * Author: 涛声依旧 likeboat@163.com
 * Time: 2022/8/24 09:26
 */
@Data
public class UserInfo {
    private String avatar;
    private String homePath;
//    private String password;
    private String realName;
    private String token;
    private BigInteger userId;
    private String username;
    private List<Role> roles;
}
