package com.sbvadmin.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sbvadmin.mapper.UserMapper;
import com.sbvadmin.model.Log;
import com.sbvadmin.model.ResultFormat;
import com.sbvadmin.model.User;
import com.sbvadmin.service.impl.LogServiceImpl;
import com.sbvadmin.utils.IpUtil;
import com.sbvadmin.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Notes:
 * Author: 涛声依旧 likeboat@163.com
 * Time: 2022/7/20 20:28
 */
public class JwtLoginFilter extends AbstractAuthenticationProcessingFilter {

    @Autowired
    UserMapper userMapper;

    @Autowired
    LogServiceImpl logService;

    @Value("${application.version}")
    private String version;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    protected JwtLoginFilter(String defaultFilterProcessesUrl, AuthenticationManager authenticationManager) {
        super(new AntPathRequestMatcher(defaultFilterProcessesUrl));
        setAuthenticationManager(authenticationManager);
    }
    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse resp) throws AuthenticationException, IOException, ServletException, IOException {
        User user = null;
        try {
            user = new ObjectMapper().readValue(req.getInputStream(), User.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
    }
    @Override
    protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse resp, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        Collection<? extends GrantedAuthority> authorities = authResult.getAuthorities();
        StringBuffer as = new StringBuffer();
        for (GrantedAuthority authority : authorities) {
            as.append(authority.getAuthority())
                    .append(",");
        }

        User user = (User) authResult.getPrincipal();
        Date expired = jwtTokenUtil.getExpiredDate();
        Map<String, Object> map = new HashMap<>();
        map.put("authorities", as); // 配置用户角色
        map.put("uid",user.getId()); // 配置用户id
        String jwt = jwtTokenUtil.genToken(map,authResult.getName(),expired);
        resp.setContentType("application/json;charset=utf-8");
        PrintWriter out = resp.getWriter();
        Map<String, Object> tokenMap = new HashMap<>();  // map自定义输出结构
        tokenMap.put("token", jwt);
        tokenMap.put("expired", expired);
        tokenMap.put("roles",authorities);
        tokenMap.put("username", authResult.getName());
        tokenMap.put("name", user.getNickname()); // 获得登录用户的其他信息
        out.write(new ObjectMapper().writeValueAsString(ResultFormat.success(tokenMap)));
        out.flush();
        out.close();

        //更新登录信息
        user.setLastLoginAt(LocalDateTime.now());
        // 获取RequestAttributes
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        // 从获取RequestAttributes中获取HttpServletRequest的信息
        HttpServletRequest request = (HttpServletRequest) requestAttributes.resolveReference(RequestAttributes.REFERENCE_REQUEST);
        user.setLastLoginIp(IpUtil.getIpRequest(request));
        userMapper.updateById(user);

        // 加入登录日志
//        Log log = Log.builder().build();
        Log log = new Log();
        log.setDescription("用户成功登录");
        // 请求信息
        log.setMethod("JwtLoginFilter");
        log.setReqParam("");
        log.setUri(request.getRequestURI());
        String ip = IpUtil.getIpRequest(request);
        log.setIp(ip);
        log.setAddress(IpUtil.getAddressByIP(ip));
        // 用户信息
        log.setUid(user.getId());
        log.setUsername(authResult.getName());
        // 时间信息
        log.setCreatedAt(LocalDateTime.now());
        log.setUpdatedAt(LocalDateTime.now());
        log.setVersion(version);
        log.setTakeUpTime(0);
        log.setLevel(Log.ACTION_LEVEL);
        logService.save(log);
    }
    protected void unsuccessfulAuthentication(HttpServletRequest req, HttpServletResponse resp, AuthenticationException failed) throws IOException, ServletException {
        resp.setContentType("application/json;charset=utf-8");
        PrintWriter out = resp.getWriter();
        out.write("登录失败!");
        out.flush();
        out.close();
    }
}
