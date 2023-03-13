package com.sbvadmin.config;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * Notes: 根据用户拥有的角色和接口需要的角色进行比对
 * Author: 涛声依旧 likeboat@163.com
 * Time: 2022/6/16 11:21
 */
@Component
public class CustomAccessDecisionManager implements AccessDecisionManager {
    @Override
    public void decide(Authentication auth, Object object, Collection<ConfigAttribute> ca) throws AccessDeniedException, InsufficientAuthenticationException {
        Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();// 当前用户拥有的角色
        for (ConfigAttribute configAttribute : ca) { // 当前URL需要的角色
            // 第1类
            if ("ROLE_LOGIN".equals(configAttribute.getAttribute())){
                return; // 无需权限的直接访问
            }
            // 第2类
            if ("ROLE_EVERYONE".equals(configAttribute.getAttribute()) && (auth instanceof UsernamePasswordAuthenticationToken)){
                    return; // 无需权限的直接访问
            }
            // 第3类
            for (GrantedAuthority authority : authorities) {
                if (configAttribute.getAttribute().equals(authority.getAuthority())){ // 找到匹配项
                    return; // 权限满足
                }
            }
        }
        // 如果上面的return未通过，到这里会抛出异常，实测如下:
        // 1.auth为UsernamePasswordAuthenticationToken，即认证成功过的，但权限不够的，会进入CustomAccessDeniedHandler
        // 2.auth为AnonymousAuthenticationToken，即未认证的，也肯定没权限，会进入CustomAuthenticationEntryPoint
        throw new AccessDeniedException("权限不足");
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return true;
    }
}
