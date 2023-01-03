package com.shenfangtao.config;

import com.shenfangtao.mapper.UserMapper;
import com.shenfangtao.model.ErrorCode;
import com.shenfangtao.model.User;
import com.shenfangtao.utils.JwtTokenUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.EOFException;
import java.io.IOException;
import java.math.BigInteger;
import java.util.List;

/**
 * Notes:
 * Author: 涛声依旧 likeboat@163.com
 * Time: 2022/7/20 20:38
 */
public class JwtFilter extends OncePerRequestFilter {
    @Autowired
    private  JwtTokenUtil jwtTokenUtil;

    @Autowired
    UserMapper userMapper;

    /** 在Filter中注入HandlerExceptionResolver **/
    @Autowired
    @Qualifier("handlerExceptionResolver")
    private HandlerExceptionResolver resolver;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

//    public void doFilterInternal(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        String jwtToken = req.getHeader("authorization");
        System.out.println(jwtToken);
        if (jwtToken != null){
            jwtToken = jwtToken.replace("Bearer","");
            if (!jwtTokenUtil.isTokenExpired(jwtToken)) {
                Claims claims = jwtTokenUtil.parserToken(jwtToken);
                String username = claims.getSubject();//获取当前登录用户名
                List<GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList((String) claims.get("authorities"));
                Long uid = Long.valueOf(String.valueOf(claims.get("uid")));
                User user  = userMapper.selectById(uid);
                UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user, jwtToken, authorities);
                token.setDetails(uid);
                SecurityContextHolder.getContext().setAuthentication(token);
            }else{
                System.out.println("令牌已失效");
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

