package com.shenfangtao.controller;

import com.shenfangtao.model.Role;
import com.shenfangtao.model.User;
import com.shenfangtao.model.UserInfo;
import com.shenfangtao.service.impl.UserServiceImpl;
import com.shenfangtao.utils.SbvLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;

/**
 * Notes: 用户管理控制器
 * Author: 涛声依旧 likeboat@163.com
 * Time: 2022/7/13 11:22
 */
@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    UserServiceImpl userService;


    @GetMapping("")
    @SbvLog(desc = "users")
    public List<User> getUsers(){
        List<User> data = userService.getUsersWithRoles();
//        int a = 9/0;
        return data;
    }

    @PostMapping("")
    public boolean addUser(@RequestBody User user){
        return userService.save(user);
    }

    @PutMapping("/{id}")
    public boolean editUser(@RequestBody User user, @PathVariable BigInteger id) {
        user.setId(id);
        return userService.updateById(user);
    }
    @DeleteMapping("/{id}")
    public boolean delUser(@PathVariable BigInteger id) {
        return userService.removeById(id);
    }

    @GetMapping("/getUserInfo")
    public UserInfo getUserInfo(){
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(user.getId());
        userInfo.setUsername(user.getUsername());
        userInfo.setAvatar(user.getAvatar());
        userInfo.setHomePath("/dashboard/analysis");
        userInfo.setRealName(user.getName());
        List<Role> roles = userService.getUserRolesByUid(user.getId());
        userInfo.setRoles(roles);
        userInfo.setToken((String)authentication.getCredentials());
        return userInfo;
    }
}
