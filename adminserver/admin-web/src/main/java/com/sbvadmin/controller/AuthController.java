package com.sbvadmin.controller;

import com.sbvadmin.model.Role;
import com.sbvadmin.model.User;
import com.sbvadmin.model.UserInfo;
import com.sbvadmin.service.impl.PermissionServiceImpl;
import com.sbvadmin.service.impl.UserServiceImpl;
import com.sbvadmin.service.utils.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

/**
 * Notes: 权限，用户信息相关
 * Author: 涛声依旧 likeboat@163.com
 * Time: 2022/8/26 20:10
 */
@RestController
@RequestMapping("/api")
public class AuthController {



    @Autowired
    UserServiceImpl userService;

    @Autowired
    PermissionServiceImpl permissionService;

    /**
     * Notes:  解决访问必须带index.html的问题
     * @param: []
     * @return: org.springframework.web.servlet.ModelAndView
     * Author: 涛声依旧 likeboat@163.com
     * Time: 2022/9/29 21:54
     **/
    @GetMapping("/")
    public ModelAndView index(){
        return new ModelAndView("redirect:/index.html");
    }
    /**
     * Notes:  获取个人信息
     * @param: []
     * @return: com.sbvadmin.model.UserInfo
     * Author: 涛声依旧 likeboat@163.com
     * Time: 2022/9/5 21:30
     **/
    @GetMapping("/getUserInfo")
    public UserInfo getUserInfo(){
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(user.getId());
        userInfo.setId(user.getId());
        userInfo.setUsername(user.getUsername());
        userInfo.setAvatar(CommonUtil.getAvatarUrl(user.getAvatar()));
        userInfo.setHomePath("/dashboard/workbench"); // 默认去到工作台
        userInfo.setRealName(user.getNickname());
        userInfo.setNickname(user.getNickname());
        userInfo.setEmail(user.getEmail());
        userInfo.setPhone(user.getPhone());
        List<Role> roles = userService.getUserRolesByUid(user.getId());
        userInfo.setRoles(roles);
        userInfo.setToken((String)authentication.getCredentials());
        return userInfo;
    }

    /**
     * Notes:  获得个人所拥有的菜单
     * @param: []
     * @return: java.util.Map
     * Author: 涛声依旧 likeboat@163.com
     * Time: 2022/9/5 21:43
     **/
    @GetMapping("/getMenuList")
    public List<Map<String, Object>> getMenuList(){
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        return permissionService.getMenusByUid(user.getId());
    }

    /**
     * Notes:  获得按钮权限点
     * @param: []
     * @return: java.util.List
     * Author: 涛声依旧 likeboat@163.com
     * Time: 2022/9/6 16:54
     **/
    @GetMapping("/getPermCode")
    public List getPermCode(){
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        return permissionService.getCodesByUid(user.getId());
    }
}
