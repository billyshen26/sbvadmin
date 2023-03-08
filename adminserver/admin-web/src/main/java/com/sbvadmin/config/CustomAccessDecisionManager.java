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
 * Notes:
 * Author: 涛声依旧 likeboat@163.com
 * Time: 2022/6/16 11:21
 */
@Component
public class CustomAccessDecisionManager implements AccessDecisionManager {
    @Override
    public void decide(Authentication auth, Object object, Collection<ConfigAttribute> ca) throws AccessDeniedException, InsufficientAuthenticationException {
        Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();// 当前用户拥有的角色
        for (ConfigAttribute configAttribute : ca) { // 当前URL需要的角色
            // TODO 这里待优化整体授权流程
            if ("ROLE_LOGIN".equals(configAttribute.getAttribute()) && (auth instanceof UsernamePasswordAuthenticationToken || auth instanceof AnonymousAuthenticationToken)){
                    return; // 无需权限的直接访问
            }
            for (GrantedAuthority authority : authorities) {
                if (configAttribute.getAttribute().equals(authority.getAuthority())){ // 找到匹配项
                    return;
                }
            }
        }
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
