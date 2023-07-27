package com.sbvadmin.monitorserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * Notes:
 * Author: 涛声依旧 likeboat@163.com
 * Time: 2023/7/6 14:09
 */
@Configuration(proxyBeanMethods = false)
public class SecurityConfig {
    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        SavedRequestAwareAuthenticationSuccessHandler successHandler = new SavedRequestAwareAuthenticationSuccessHandler();
        successHandler.setTargetUrlParameter("redirectTo");
        successHandler.setDefaultTargetUrl("/");
        return http.authorizeHttpRequests((authorizeRequests) -> authorizeRequests
                        .requestMatchers(new AntPathRequestMatcher("/login"))
                        .permitAll()
                        .anyRequest()
                        .authenticated())
                .formLogin((formLogin) -> formLogin.loginPage("/login").successHandler(successHandler)) // <3>
                .logout((logout) -> logout.logoutUrl("/logout"))
                .httpBasic(Customizer.withDefaults())
                .csrf().disable().build();
    }
}
