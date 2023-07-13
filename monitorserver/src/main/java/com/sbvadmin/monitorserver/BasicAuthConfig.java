package com.sbvadmin.monitorserver;

import de.codecentric.boot.admin.server.domain.entities.Instance;
import de.codecentric.boot.admin.server.web.client.BasicAuthHttpHeaderProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.util.Base64Utils;
import org.springframework.util.StringUtils;

import java.nio.charset.StandardCharsets;

/**
 * Notes:
 * Author: 涛声依旧 likeboat@163.com
 * Time: 2023/7/12 10:39
 */
@Configuration
public class BasicAuthConfig {
    /**
     * springboot自动装配默认实现类，由于需要对配置密码进行解码操作，故手动装配
     *
     * @return
     */
    @Bean
    public BasicAuthHttpHeaderProvider basicAuthHttpHeadersProvider() {
        return new BasicAuthHttpHeaderProvider() {
            @Override
            public HttpHeaders getHeaders(Instance instance) {
                HttpHeaders headers = new HttpHeaders();
                //获取用户名，密码
                String username = instance.getRegistration().getMetadata().get("user.name");
                String password = instance.getRegistration().getMetadata().get("user.password");
                String type = instance.getRegistration().getMetadata().get("type");
                String token = instance.getRegistration().getMetadata().get("token");

                //若是token有值，那么使用token认知
                if ("token".equalsIgnoreCase(type)) {
                    headers.set(HttpHeaders.AUTHORIZATION,token);
                } else if (StringUtils.hasText(username) && StringUtils.hasText(password)) {
                    headers.set(HttpHeaders.AUTHORIZATION, encode(username, password));
                }
                return headers;
            }

            protected String encode(String username, String password) {
                String token = Base64Utils.encodeToString((username + ":" + password).getBytes(StandardCharsets.UTF_8));
                return "Basic " + token;
            }
        };
    }
}