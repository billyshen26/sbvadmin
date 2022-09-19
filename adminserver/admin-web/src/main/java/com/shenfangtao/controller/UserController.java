package com.shenfangtao.controller;

import com.shenfangtao.model.Role;
import com.shenfangtao.model.User;
import com.shenfangtao.model.UserInfo;
import com.shenfangtao.service.impl.UserServiceImpl;
import com.shenfangtao.utils.SbvLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public List<User> getUsers(){
        return userService.getUsersWithRoles();
    }

    @PostMapping("")
    @SbvLog(desc = "新增用户")
    public boolean addUser(@RequestBody @Valid User user){
//        if(bindingResult.hasErrors()){
//            System.out.println(bindingResult.getAllErrors().get(0).getDefaultMessage());
//        }
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


}
