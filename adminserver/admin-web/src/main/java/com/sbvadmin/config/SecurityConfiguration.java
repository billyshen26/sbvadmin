package com.sbvadmin.config;

import com.sbvadmin.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    // 配置加密方式
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    UserServiceImpl userService;

    @Autowired
    private AuthenticationConfiguration auth;

    @Autowired
    private CustomAuthenticationEntryPoint customAuthenticationEntryPoint;

    @Autowired
    CustomAccessDeniedHandler accessDeniedHandler;

     @Autowired
     UserDetailsService userServiceDetails;
    @Autowired
    SmsAuthenticationProvider smsAuthenticationProvider;

     @Bean
     DaoAuthenticationProvider daoAuthenticationProvider(){
         DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
         daoAuthenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder());
         daoAuthenticationProvider.setUserDetailsService(userServiceDetails);
         return daoAuthenticationProvider;
     }
    @Bean
     protected AuthenticationManager authenticationManager() throws Exception {
        smsAuthenticationProvider.setUserDetailsService(userServiceDetails);
        // 加入两个provider
        ProviderManager authenticationManager = new ProviderManager(Arrays.asList(daoAuthenticationProvider(),smsAuthenticationProvider));
        authenticationManager.setEraseCredentialsAfterAuthentication(false);
        return authenticationManager;
     }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                    @Override
                    public <O extends FilterSecurityInterceptor> O postProcess(O object) {
                        object.setSecurityMetadataSource(cfisms());
                        object.setAccessDecisionManager(cadm());
                        return object;
                    }
                })
//                .antMatchers(HttpMethod.POST, "/api/login","/api/configs/getConfigBySymbol").permitAll()
                .anyRequest().authenticated()
                .and()
                .logout()
                .logoutUrl("/api/logout")
                .clearAuthentication(true)
                .invalidateHttpSession(true)
                .addLogoutHandler(new LogoutHandler() {
                    @Override
                    public void logout(HttpServletRequest req,
                                       HttpServletResponse resp,
                                       Authentication auth) {
                        System.out.println("Logout");

                    }
                })
                .logoutSuccessHandler(new LogoutSuccessHandler() {
                    @Override
                    public void onLogoutSuccess(HttpServletRequest req,
                                                HttpServletResponse resp,
                                                Authentication auth)
                            throws IOException {
                        System.out.println("logoutSuccess");
//                        resp.sendRedirect("/api/login");
                    }
                })
                .and()
                .addFilterBefore(jwtLoginFilter(), UsernamePasswordAuthenticationFilter.class)
//                .addFilterBefore(new JwtLoginFilter("/api/login",authenticationManager()), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(jwtFilter(),UsernamePasswordAuthenticationFilter.class)
                .csrf().disable();
//                .userDetailsService(userService);
        // 禁用缓存
        http.headers().cacheControl();
        //添加自定义未授权和未登录结果返回
        http.exceptionHandling()
                .authenticationEntryPoint(customAuthenticationEntryPoint);
        http.exceptionHandling()
                .accessDeniedHandler(accessDeniedHandler);
        return http.build();
    }

    /**
     * 配置要忽略的路径
     */
    @Bean
    WebSecurityCustomizer webSecurityCustomizer() {
        // 忽略 /error 页面
        return web -> web.ignoring().requestMatchers("/","/error","/index.html","/avatar.png","/logo.png","/uploads/**","/assets/**","/_app.config.js","/resource/**")
                // 忽略常见的静态资源路径
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }
    @Bean
    CustomFilterInvocationSecurityMetadataSource cfisms() {
        return new CustomFilterInvocationSecurityMetadataSource();
    }

    @Bean
    CustomAccessDecisionManager cadm(){
        return new CustomAccessDecisionManager();
    }

    /**
     * JWT filter
     * 这种写法可以解决filter中无法注入其他bean的问题
     */
    @Bean
    public JwtFilter jwtFilter(){
        return new JwtFilter();
    }

    @Bean
    public JwtLoginFilter jwtLoginFilter() throws Exception {return new JwtLoginFilter("/api/login",authenticationManager()); }
}
