package com.sbvadmin.controller;

import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.spring.SpringUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sbvadmin.model.*;
import com.sbvadmin.service.impl.UserDeptServiceImpl;
import com.sbvadmin.service.impl.UserRoleServiceImpl;
import com.sbvadmin.service.impl.UserServiceImpl;
import com.sbvadmin.service.utils.CommonService;
import com.sbvadmin.service.utils.CommonUtil;
import com.sbvadmin.utils.RequestJson;
import com.sbvadmin.utils.SbvLog;
import org.springframework.amqp.AmqpConnectException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

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
    RedisTemplate redisTemplate;

    @Autowired
    UserServiceImpl userService;

    @Autowired
    UserRoleServiceImpl userRoleService;

    @Autowired
    UserDeptServiceImpl userDeptService;

    @Autowired
    CommonService commonService;

//    @GetMapping("")
//    public List<User> getUsers(@RequestParam(value="deptId" ,required=false) Long did,
//                               @RequestParam(value="id" ,required=false) Long id,
//                               @RequestParam(value="name" ,required=false) String name){
//        if (did == null) did = CommonUtil.getOwnUser().getLoginDeptId(); // 默认获取登录机构的账号
//        List<User> users = userService.getUsersWithRoles(did,id,name);
//        for (User user : users) {
//            user.setAvatar(commonService.getAvatarUrl(user.getAvatar()));
//        }
//        return users;
//    }

    @GetMapping("")
    public IPage<User> getItems( @RequestParam(value="deptId" ,required=false) Long did,
                                 @RequestParam(value="id" ,required=false) Long id,
                                 @RequestParam(value="page" ,required=false) Long page,
                                 @RequestParam(value="pageSize" ,required=false) Long pageSize,
                                 @RequestParam(value="name" ,required=false) String name ){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        // id精准搜索
        if (id != null)
            queryWrapper.eq("sys_user.id",id);
        // 昵称搜索
        if (name != null)
            queryWrapper.like("sys_user.nickname",name);
        // 数据权限限定
        if (did == null) did = CommonUtil.getOwnUser().getLoginDeptId(); // 默认获取登录机构的账号
        Page<User> itemPage = new Page<>(page,pageSize);
        IPage<User> iPage = userService.page(itemPage, queryWrapper);
        return iPage;
    }

    /**
     * Notes:  格式化头像
     * @param: [iPage]
     * @return: com.baomidou.mybatisplus.core.metadata.IPage<com.sbvadmin.model.User>
     * Author: 涛声依旧 likeboat@163.com
     * Time: 2024/8/3 10:14
     **/
    public IPage<User> afterGetItems(IPage<User> iPage){
        // 格式化图片列表
        for (User user : iPage.getRecords()) {
            user.setAvatar(commonService.getAvatarUrl(user.getAvatar()));
        }
        return iPage;
    }
    @PostMapping("")
    @SbvLog(desc = "新增用户")
    public Object addItem(@RequestBody @Valid User user) {
        String message = null;
        // 密码不能为空
        if (user.getPassword() == null||user.getPassword().equals("")) return ResultFormat.fail(ErrorCode.PASSWORD_CANT_BLANK);
        // username 不能重复 TODO 应该有更优雅的写法
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("username",user.getUsername());
        if(userService.getOne(userQueryWrapper) != null) return ResultFormat.fail(ErrorCode.USERNAME_DUPLICATED);
        // 默认头像
        if(user.getAvatar() == null) user.setAvatar("avatar.png");
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
    public Object editItem(@RequestBody User user, @PathVariable Long id) {
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
    public Object delItem(@PathVariable Long id) {
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
        redisTemplate.delete("user::" + user.getUsername()); // TIPS: 手动删除缓存
        user.setPassword(passwordEncoder.encode(jsonObject.getStr("passwordNew")));
        return userService.updateById(user);
    }
}
