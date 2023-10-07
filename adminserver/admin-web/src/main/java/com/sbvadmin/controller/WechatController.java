package com.sbvadmin.controller;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sbvadmin.common.service.JwtTokenService;
import com.sbvadmin.model.ErrorCode;
import com.sbvadmin.model.ResultFormat;
import com.sbvadmin.model.User;
import com.sbvadmin.service.impl.UserServiceImpl;
import com.sbvadmin.utils.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * Notes: 跟微信小程序相关的api
 * Author: 涛声依旧 likeboat@163.com
 * Time: 2023/3/29 21:41
 */
@RestController
@RequestMapping("/api/wechat")
@Slf4j
public class WechatController {
    @Value("${wechat.app-id}")
    private String appId;

    @Value("${wechat.app-secret}")
    private String appSecret;

    @Autowired
    private JwtTokenService jwtTokenService;

    @Autowired
    private UserServiceImpl userService;
    /*
     * Notes: 用code换取openid
     * @param: [jscode]
     * @return: java.lang.String
     * Author: 涛声依旧 likeboat@163.com
     * Time: 2023/3/29 21:50
     **/
    @GetMapping("/jscode2openid")
    public Object jscode2openid(String jscode){
        String url = "https://api.weixin.qq.com/sns/jscode2session?appid="
                +appId + "&secret=" + appSecret + "&js_code=" + jscode + "&grant_type=authorization_code";
        JSONObject object = JSONUtil.parseObj(HttpUtil.get(url));
        if (object.getInt("errcode") != null && object.getInt("errcode") != 0){
            return ResultFormat.fail(ErrorCode.WECHAT_AUTH_FAILED);
        }
        log.info("jscode2openid:" + object.getStr("openid"));
        log.info(JSONUtil.toJsonStr(object));
        return object;
    }

    /**
     * Notes:  微信登录，创建用户，获取token
     * 1.username可以默认为openid，后面获取手机号后可以改成手机号
     * 2.头像和昵称需要稍后获取 https://segmentfault.com/q/1010000043034402/a-1020000043034645
     * 3.通过    获取token
     * @param: []
     * @return: java.lang.Object
     * Author: 涛声依旧 likeboat@163.com
     * Time: 2023/3/29 22:19
     **/
    @PostMapping("/wechatLogin")
    public Object wechatLogin(@RequestBody User user){
        if(user.getMpOpenId().length() != 28) {
            log.error("微信授权openid长度不对: " + user.getMpOpenId());
            return "微信授权openid长度不对";
        }

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("mp_open_id",user.getMpOpenId());
        User existUser =  userService.getOne(queryWrapper);
        if (existUser != null){
            existUser.setNickname(user.getNickname()); // 更新昵称
            existUser.setAvatar(user.getAvatar()); // 更新头像
            existUser.setUnionId(user.getUnionId()); // 更新unionid
            userService.updateById(existUser);
            user = existUser;
        }else{ // 若不存在则直接新增一个用户
            user.setUsername(user.getMpOpenId()); // 默认用户名为openid
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(); // 密码加密
            user.setPassword(passwordEncoder.encode("123456")); // 默认密码123456 TODO
            List<Long> roleIds = Arrays.asList(3L);
            user.setRoleIds(roleIds);
            List<Long> deptIds = Arrays.asList(1L);
            user.setDeptIds(deptIds);
            userService.save(user);
        }
        // 生成token
        Date expired = jwtTokenService.getExpiredDate();
        log.info("token 过期时间:"+ expired.toString());
        Map<String, Object> map = new HashMap<>();
        map.put("authorities", "ROLE_user"); // 配置用户角色
        map.put("uid",user.getId()); // 配置用户id
        String token = jwtTokenService.genToken(map,user.getUsername(),expired);

        // 登录后用户id也返回下
        Map<String, Object> result = new HashMap<>();
        result.put("token", token); // 令牌
        result.put("uid",user.getId()); // 用户id
        return result;
    }
}
