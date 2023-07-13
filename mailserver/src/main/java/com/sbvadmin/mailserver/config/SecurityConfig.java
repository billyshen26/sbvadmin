package com.sbvadmin.mailserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * Notes:
 * Author: 涛声依旧 likeboat@163.com
 * Time: 2023/7/6 14:09
 */
@Configuration
public class SecurityConfig {
    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests((authorizeRequests) -> authorizeRequests
//                .requestMatchers(new AntPathRequestMatcher("/test/hello"))
//                .permitAll()
                .anyRequest()
                .authenticated())
                .formLogin()
                .and()
                .httpBasic(Customizer.withDefaults())
                .csrf().disable().build();
    }
}
