package com.sbvadmin.monitorserver.config;

import com.sbvadmin.common.service.JwtTokenService;
import de.codecentric.boot.admin.server.domain.entities.Instance;
import de.codecentric.boot.admin.server.web.client.HttpHeadersProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.util.Base64Utils;
import org.springframework.util.StringUtils;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Notes:
 * Author: 涛声依旧 likeboat@163.com
 * Time: 2023/7/26 19:57
 */
@Configuration
public class MyBasicAuthHttpHeaderProvider implements HttpHeadersProvider {

    @Autowired
    JwtTokenService jwtTokenService;

    @Override
    public HttpHeaders getHeaders(Instance instance) {
        HttpHeaders headers = new HttpHeaders();
        //获取用户名，密码
        String username = instance.getRegistration().getMetadata().get("user.name"); //使用默认的user账号， 待优化 TODO
        String password = instance.getRegistration().getMetadata().get("user.password");
        String type = instance.getRegistration().getMetadata().get("type");
        String token = instance.getRegistration().getMetadata().get("token");

        //若是token有值，那么使用token认证
        if ("token".equalsIgnoreCase(type)) {
            headers.set(HttpHeaders.AUTHORIZATION,token);
        } else if ("jwt".equalsIgnoreCase(type)) {
            Date expired = jwtTokenService.getExpiredDate();
            Map<String, Object> map = new HashMap<>();
            map.put("authorities", "Role_user"); // 配置用户角色
            map.put("uid",3); // 配置用户id
            String jwt = jwtTokenService.genToken(map,username,expired);
            headers.set(HttpHeaders.AUTHORIZATION, jwt);
        }else if (StringUtils.hasText(username) && StringUtils.hasText(password)) {
            headers.set(HttpHeaders.AUTHORIZATION, encode(username, password));
        }
        return headers;
    }

    protected String encode(String username, String password) {
        String token = Base64Utils.encodeToString((username + ":" + password).getBytes(StandardCharsets.UTF_8));
        return "Basic " + token;
    }
}
