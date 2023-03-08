package com.sbvadmin.controller;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.sbvadmin.model.ErrorCode;
import com.sbvadmin.model.ResultFormat;
import com.sbvadmin.model.User;
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

    @GetMapping("")
    public List<User> getUsers(@RequestParam(value="deptId" ,required=false) Long did) {
        String host = environment.getProperty("server.host");
        String port = environment.getProperty("server.port");
        List<User> users = userService.getUsersWithRoles(did);
        for (User user : users) {
//            if (!user.getAvatar().contains("http")) {
//                user.setAvatar(host + ":" + port + File.separator + user.getAvatar()); // 补充域名和端口
//            }
            user.setAvatar(CommonUtil.getAvatarUrl(user.getAvatar()));
        }
        return users;
    }

    @PostMapping("")
    @SbvLog(desc = "新增用户")
    public Object addUser(@RequestBody @Valid User user) {
        String message = null;
        // 1.将用户添加到数据库
        if (!userService.save(user))
            return ResultFormat.fail(ErrorCode.FAILED.getCode(), ErrorCode.FAILED.getMessage());
        // 2.将用户添加事件发送到mq，用于后续邮件通知
        try {
            rabbitTemplate.convertAndSend("add-user", user);
        } catch (AmqpConnectException e) {
            return ResultFormat.fail(ErrorCode.RABBITMQ_NOT_ACTIVE.getCode(), ErrorCode.RABBITMQ_NOT_ACTIVE.getMessage());
        }
        return true;
    }

    @PutMapping("/{id}")
    @SbvLog(desc = "修改用户")
    public boolean editUser(@RequestBody User user, @PathVariable Long id) {
        user.setId(id);
        if (user.getPassword() != null) {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(); // 密码加密
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        return userService.updateById(user); // TODO 修改角色机构
    }

    @DeleteMapping("/{id}")
    @SbvLog(desc = "删除用户")
    public Object delUser(@PathVariable Long id) {
        if (id == 1L) {
            return ResultFormat.fail(ErrorCode.ROOT_CANT_DELETE.getCode(), ErrorCode.ROOT_CANT_DELETE.getMessage());
        }
        return userService.removeById(id);
    }

    @PostMapping("/changePassword")
    @SbvLog(desc = "修改个人密码")
    public Object changePassword(@RequestBody String data) {
        JSONObject jsonObject = JSONUtil.parseObj(data);
        if (jsonObject.getShort("id") == 1) {
            return ResultFormat.fail(ErrorCode.ROOT_CANT_UPDATE.getCode(), ErrorCode.ROOT_CANT_UPDATE.getMessage());
        }
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
