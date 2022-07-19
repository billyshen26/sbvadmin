package com.shenfangtao.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * Notes: 用户模型
 * Author: 涛声依旧 likeboat@163.com
 * Time: 2022/7/12 20:07
 */
@Data
public class User implements UserDetails {
    @TableId(type = IdType.AUTO)
    private BigInteger id;
    private String name;
    private String phone;
    private String email;
    @Getter(value = AccessLevel.NONE)
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
    private Boolean locked;
    @TableField(exist = false)
    private List<Role> roles;
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
        return enabled;
    }

}
