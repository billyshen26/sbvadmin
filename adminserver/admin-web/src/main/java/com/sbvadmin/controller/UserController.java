package com.sbvadmin.controller;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sbvadmin.model.*;
import com.sbvadmin.service.impl.UserDeptServiceImpl;
import com.sbvadmin.service.impl.UserRoleServiceImpl;
import com.sbvadmin.service.impl.UserServiceImpl;
import com.sbvadmin.service.utils.CommonUtil;
import com.sbvadmin.utils.SbvLog;
import org.springframework.amqp.AmqpConnectException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collection;
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
    RabbitTemplate rabbitTemplate;

    @Autowired
    UserServiceImpl userService;

    @Autowired
    UserRoleServiceImpl userRoleService;

    @Autowired
    UserDeptServiceImpl userDeptService;

    @GetMapping("")
    public List<User> getUsers(@RequestParam(value="deptId" ,required=false) Long did,
                               @RequestParam(value="id" ,required=false) Long id,
                               @RequestParam(value="name" ,required=false) String name) {
        List<User> users = userService.getUsersWithRoles(did,id,name);
        for (User user : users) {
            user.setAvatar(CommonUtil.getAvatarUrl(user.getAvatar()));
        }
        return users;
    }

    @PostMapping("")
    @SbvLog(desc = "新增用户")
    public Object addUser(@RequestBody @Valid User user) {
        String message = null;
        // 密码不能为空
        if (user.getPassword() == null||user.getPassword().equals("")) return ResultFormat.fail(ErrorCode.PASSWORD_CANT_BLANK);
        // username 不能重复 TODO 应该有更优雅的写法
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("username",user.getUsername());
        if(userService.getOne(userQueryWrapper) != null) return ResultFormat.fail(ErrorCode.USERNAME_DUPLICATED);
        // 默认头像
        if(user.getAvatar() == null) user.setAvatar("");
        // 1.将用户添加到数据库
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(); // 密码加密
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if (!userService.save(user))
            return ResultFormat.fail(ErrorCode.FAILED);
        // 2.将用户添加事件发送到mq，用于后续邮件通知
        try {
            rabbitTemplate.convertAndSend("add-user", user);
        } catch (AmqpConnectException e) {
            return ResultFormat.fail(ErrorCode.RABBITMQ_NOT_ACTIVE);
        }
        return true;
    }

    @PutMapping("/{id}")
    @SbvLog(desc = "修改用户")
    public boolean editUser(@RequestBody User user, @PathVariable Long id) {
        user.setId(id);
        // 修改密码
        if (user.getPassword() != null) {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(); // 密码加密
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
//        // 修改角色
//        QueryWrapper userRoleWrapper = new QueryWrapper<>();
//        userRoleWrapper.eq("uid",id);
//        userRoleService.remove(userRoleWrapper); //先删除之前的分配关系
//        List<UserRole> userRoles = new ArrayList<>();
//        for (Long roleId : user.getRoleIds()) {
//            UserRole userRole = new UserRole();
//            userRole.setRid(roleId);
//            userRole.setUid(id);
//            userRoles.add(userRole);
//        }
//        userRoleService.saveBatch(userRoles);
//
//        // 修改部门
//        QueryWrapper userDeptWrapper = new QueryWrapper<>();
//        userDeptWrapper.eq("uid",id);
//        userDeptService.remove(userDeptWrapper); //先删除之前的分配关系
//        List<UserDept> userDepts = new ArrayList<>();
//        for (Long deptId : user.getDeptIds()) {
//            UserDept userDept = new UserDept();
//            userDept.setDid(deptId);
//            userDept.setUid(id);
//            userDepts.add(userDept);
//        }
//        userDeptService.saveBatch(userDepts);

        return userService.updateById(user);
    }

    @DeleteMapping("/{id}")
    @SbvLog(desc = "删除用户")
    public Object delUser(@PathVariable Long id) {
        if (id == 1L) {
            return ResultFormat.fail(ErrorCode.ROOT_CANT_DELETE);
        }
        return userService.removeById(id);
    }

    @PostMapping("/changePassword")
    @SbvLog(desc = "修改个人密码")
    public Object changePassword(@RequestBody String data) {
        JSONObject jsonObject = JSONUtil.parseObj(data);
//        if (jsonObject.getShort("id") == 1) {
//            return ResultFormat.fail(ErrorCode.ROOT_CANT_UPDATE);
//        }
        User user = userService.getById(jsonObject.getShort("id"));
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(); // 密码加密
        if(!passwordEncoder.matches(jsonObject.getStr("passwordOld"),user.getPassword())){
            return "旧密码错误";
        }
        // 修改密码
        user.setPassword(passwordEncoder.encode(jsonObject.getStr("passwordNew")));
        return userService.updateById(user);
    }
}
