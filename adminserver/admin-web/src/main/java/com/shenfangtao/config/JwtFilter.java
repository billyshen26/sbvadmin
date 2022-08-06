package com.shenfangtao.config;

import com.shenfangtao.model.ErrorCode;
import com.shenfangtao.utils.JwtTokenUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

//    public void doFilterInternal(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        String jwtToken = req.getHeader("authorization");
        System.out.println(jwtToken);
        if (jwtToken != null){
            jwtToken = jwtToken.replace("Bearer","");
            if (!jwtTokenUtil.isTokenExpired(jwtToken)) {
                Claims claims = Jwts.parser().setSigningKey("sang@123").parseClaimsJws(jwtToken)
                        .getBody();
                String username = claims.getSubject();//获取当前登录用户名
                List<GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList((String) claims.get("authorities"));
                BigInteger uid = new BigInteger(String.valueOf(claims.get("uid")));
                UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, null, authorities);
                token.setDetails(uid);
                SecurityContextHolder.getContext().setAuthentication(token);
            }else{
                System.out.println("令牌已失效");
                // todo 捕获异常
//                throw new BadCredentialsException(ErrorCode.TOKEN_INVALID.getMessage());
            }

        }else{
            SecurityContextHolder.clearContext();// 如果没有token，则清空认证，否则会使用上一次缓存的token
//            SecurityContextHolder.getContext().setAuthentication(null);
        }
        filterChain.doFilter(req,response);
    }

}

