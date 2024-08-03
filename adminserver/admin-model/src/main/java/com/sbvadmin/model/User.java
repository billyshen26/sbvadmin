package com.sbvadmin.model;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Notes: 用户模型
 * Author: 涛声依旧 likeboat@163.com
 * Time: 2022/7/12 20:07
 */
@Data
@TableName("sys_user")
@JsonIgnoreProperties({"enabled","accountNonExpired", "accountNonLocked", "credentialsNonExpired", "authorities"})
public class User extends BaseModel implements UserDetails , Serializable {
    @TableId(type = IdType.AUTO)
    private Long id;
    @NotNull(message = "昵称不能为空")
    private String nickname;
    private String wechat;
    private String qq;
    private String phone;
    private String email;
    private Boolean activated;
    @NotNull(message = "用户名不能为空")
    private String username;
    @NotNull(message = "密码不能为空")
    private String password;
    @TableField(exist = false)
    private String rawPassword; // 未加密前的密码
    private String avatar;
    private String homePath;
    private LocalDateTime lastLoginAt;
    private String lastLoginIp;
    private String mpOpenId;
    private String oaOpenId;
    private String unionId;
    private Boolean locked;
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createdAt;
//    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8") // 解决返回带T的问题 改成LocalDateTimeSerializerConfig
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updatedAt;

    @TableField(exist = false)
    private List<Long> roleIds; // 方便前端显示，分配用户角色
    @TableField(exist = false)
    private List<Role> roles;

    @TableField(exist = false)
    private List<Long> deptIds; // 方便前端显示，分配用户机构
    @TableField(exist = false)
    private List<Dept> depts;

    @TableField(exist = false)
    private Long loginDeptId; // 当前登录部门id

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (Role role: roles) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return authorities;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
            return activated;
    }
}
