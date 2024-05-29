package com.sbvadmin.config;

import com.sbvadmin.common.service.JwtTokenService;
import com.sbvadmin.mapper.UserMapper;
import com.sbvadmin.model.ErrorCode;
import com.sbvadmin.model.User;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Notes:
 * Author: 涛声依旧 likeboat@163.com
 * Time: 2022/7/20 20:38
 */
@Slf4j
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    UserMapper userMapper;

    /** 在Filter中注入HandlerExceptionResolver **/
    @Autowired
    @Qualifier("handlerExceptionResolver")
    private HandlerExceptionResolver resolver;

    @Autowired
    JwtTokenService jwtTokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

//    public void doFilterInternal(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        //获取请求路径
        String path = req.getRequestURI();
        if (path.equals("/api/refreshToken")){
            filterChain.doFilter(req,response);
        }
        //获取请求方式
        String method = req.getMethod();

        String jwtToken = req.getHeader("authorization");
        log.debug("jwtToken:" + jwtToken);
        if (jwtToken != null && jwtToken != ""){
            jwtToken = jwtToken.replace("Bearer","");
            if (!jwtTokenService.isTokenExpired(jwtToken)) {
                Claims claims = jwtTokenService.parserToken(jwtToken);
                String username = claims.getSubject();//获取当前登录用户名
                List<GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList((String) claims.get("authorities"));
                Long uid = Long.valueOf(String.valueOf(claims.get("uid")));
//                User user  = userMapper.selectById(uid);
//                User user = userMapper.getOwnUserWithRoles(username);
                User user = userMapper.getOwnUserWithRolesByUid(uid);
                user.setLoginDeptId(user.getDeptIds().get(0)); // TODO 目前默认为第一个机构ID
                UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user, jwtToken, authorities);
                token.setDetails(uid);
                SecurityContextHolder.getContext().setAuthentication(token);
            }else{
                log.info("令牌已失效");
                resolver.resolveException(request, response, null, new BadCredentialsException(ErrorCode.TOKEN_INVALID.getMessage()));
                return;
//                throw new BadCredentialsException(ErrorCode.TOKEN_INVALID.getMessage());
            }

        }else{
            SecurityContextHolder.clearContext();// 如果没有token，则清空认证，否则会使用上一次缓存的token
//            SecurityContextHolder.getContext().setAuthentication(null);
        }
        filterChain.doFilter(req,response);
    }

}

