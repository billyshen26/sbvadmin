package com.shenfangtao.controller;

import com.shenfangtao.model.ErrorCode;
import com.shenfangtao.model.ResultFormat;
import com.shenfangtao.model.User;
import com.shenfangtao.service.impl.UserServiceImpl;
import com.shenfangtao.utils.SbvLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.File;
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
    Environment environment;

    @Autowired
    UserServiceImpl userService;


    @GetMapping("")
    public List<User> getUsers() {
        String host = environment.getProperty("server.host");
        String port = environment.getProperty("server.port");
        List<User> users = userService.getUsersWithRoles();
        for (User user :
                users) {
            if (!user.getAvatar().contains("http")) {
                user.setAvatar(host + ":" + port + File.separator + user.getAvatar()); // 补充域名和端口
            }
        }
        return users;
    }

    @PostMapping("")
    @SbvLog(desc = "新增用户")
    public boolean addUser(@RequestBody @Valid User user) {
//        if(bindingResult.hasErrors()){
//            System.out.println(bindingResult.getAllErrors().get(0).getDefaultMessage());
//        }
        return userService.save(user);
    }

    @PutMapping("/{id}")
    @SbvLog(desc = "修改用户")
    public boolean editUser(@RequestBody User user, @PathVariable Long id) {
        user.setId(id);
        if (user.getPassword() != null) {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(); // 密码加密
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        return userService.updateById(user);
    }

    @DeleteMapping("/{id}")
    @SbvLog(desc = "删除用户")
    public Object delUser(@PathVariable Long id) {
        if (id == 1L) {
            return ResultFormat.fail(ErrorCode.ROOT_CANT_DELETE.getCode(), ErrorCode.ROOT_CANT_DELETE.getMessage());
        }
        return userService.removeById(id);
    }
}
