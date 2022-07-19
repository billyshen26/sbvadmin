package com.shenfangtao.config;

import com.shenfangtao.mapper.PermissionMapper;
import com.shenfangtao.model.Permission;
import com.shenfangtao.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Notes:
 * Author: 涛声依旧 likeboat@163.com
 * Time: 2022/6/16 10:44
 */
@Component
public class CustomFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
    AntPathMatcher antPathMatcher = new AntPathMatcher();
    @Autowired
    PermissionMapper permissionMapper;

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        FilterInvocation filterInvocation = (FilterInvocation) object;
        String requestUrl =filterInvocation.getRequestUrl(); // 请求url
        String method = filterInvocation.getHttpRequest().getMethod(); // 请求的方法
        List<Permission> allPermission = permissionMapper.getAllPermissions();
        List<String> roleArr = new ArrayList<String>();
        for (Permission permission : allPermission) {
            if(antPathMatcher.match(permission.getPattern(),requestUrl)){ // 先判断URL路径是否符合
                if ("ANY".equals(permission.getMethod())
                        || method.equals(permission.getMethod())){  // 再判断方法是否符合
                    List<Role> roles = permission.getRoles();
                    for (int i = 0; i < roles.size(); i++) {
                        roleArr.add(roles.get(i).getName());
                    }
                }
            }
        }
        if (roleArr.size() > 0){ // 找到匹配的角色
            String[] roleNames = new String[roleArr.size()];
            roleArr.toArray(roleNames);
            return SecurityConfig.createList(roleNames);
        }
        return SecurityConfig.createList("ROLE_LOGIN");
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return FilterInvocation.class.isAssignableFrom(clazz);
    }
}
