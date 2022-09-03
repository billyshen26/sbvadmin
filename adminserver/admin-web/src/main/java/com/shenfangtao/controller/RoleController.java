package com.shenfangtao.controller;

import com.shenfangtao.model.Role;
import com.shenfangtao.model.User;
import com.shenfangtao.service.impl.RoleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Notes: 角色管理
 * Author: 涛声依旧 likeboat@163.com
 * Time: 2022/8/31 11:37
 */
@RestController
@RequestMapping("/api/roles")
public class RoleController {
    @Autowired
    RoleServiceImpl roleService;

    @GetMapping("")
    public List<Role> getRoles(){
        return roleService.list();
    }
}
