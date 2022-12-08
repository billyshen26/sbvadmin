package com.shenfangtao.controller;

import com.shenfangtao.model.*;
import com.shenfangtao.service.impl.UserServiceImpl;
import com.shenfangtao.utils.SbvLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    @SbvLog(desc = "修改用户")
    public boolean editUser(@RequestBody User user, @PathVariable Long id) {
        user.setId(id);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(); // 密码加密
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userService.updateById(user);
    }
    @DeleteMapping("/{id}")
    @SbvLog(desc = "删除用户")
    public Object delUser(@PathVariable Long id) {
        if (id == 1L){
            return ResultFormat.fail(ErrorCode.ROOT_CANT_DELETE.getCode(),ErrorCode.ROOT_CANT_DELETE.getMessage());
        }
        return userService.removeById(id);
    }

}
