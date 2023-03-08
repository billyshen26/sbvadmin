package com.sbvadmin.model;

import lombok.Data;

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
    private String nickname;
    private String realName;// 同nickname，方便前端代码编写
    private String token;
    private Long userId;
    private Long id; // 同userId，方便前端代码编写
    private String username;
    private String email;
    private String phone;
    private List<Role> roles;
}
