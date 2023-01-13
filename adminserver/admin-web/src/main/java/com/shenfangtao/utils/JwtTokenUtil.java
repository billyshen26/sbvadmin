package com.shenfangtao.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

/**
 * Notes:
 * Author: 涛声依旧 likeboat@163.com
 * Time: 2022/7/25 20:23
 */
@Slf4j
@Component
public class JwtTokenUtil {

    @Value("${spring.jwt.signingKey}")
    private String signingKey;

    @Value("${spring.jwt.expire}")
    private Integer expire;

    /**
     * 判断token是否已经失效
     */
    public boolean isTokenExpired(String token) {
        Date expiredDate = getClaimsFromToken(token).getExpiration();
        return expiredDate.before(new Date());
    }
    private Claims getClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(signingKey) // 设置标识名
                    .parseClaimsJws(token)  //解析token
                    .getBody();
        } catch (ExpiredJwtException e) {
            claims = e.getClaims();
        }
        return claims;
    }

    /**
     * Notes:  生成jwt token
     * @param: [claims, subject, date]
     * @return: java.lang.String
     * Author: 涛声依旧 likeboat@163.com
     * Time: 2022/12/30 15:52
     **/
    public String genToken(Map<String, Object> claims, String subject,Date date){
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setExpiration(date)
                .signWith(SignatureAlgorithm.HS512,signingKey)
                .compact();
    }

    /**
     * Notes:  解析jwt token
     * @param: [token]
     * @return: io.jsonwebtoken.Claims
     * Author: 涛声依旧 likeboat@163.com
     * Time: 2022/12/30 16:50
     **/
    public Claims parserToken(String token){
        return Jwts.parser().setSigningKey(signingKey).parseClaimsJws(token)
                .getBody();
    }

    /**
     * Notes:  生成token过期时间
     * @param: []
     * @return: java.util.Date
     * Author: 涛声依旧 likeboat@163.com
     * Time: 2023/1/13 09:34
     **/
    public Date getExpiredDate(){
        Date expired = new Date(System.currentTimeMillis() + expire);
        return expired;
    }
}
