package com.sbvadmin.config;

import com.sbvadmin.model.Permission;
import com.sbvadmin.model.Role;
import com.sbvadmin.service.impl.PermissionServiceImpl;
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
 * Notes: 设定访问接口需要的角色
 * Author: 涛声依旧 likeboat@163.com
 * Time: 2022/6/16 10:44
 */
@Component
public class CustomFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
    AntPathMatcher antPathMatcher = new AntPathMatcher();
    @Autowired
    PermissionServiceImpl permissionService;

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        FilterInvocation filterInvocation = (FilterInvocation) object;
//        String requestUrl =filterInvocation.getRequestUrl(); // 请求url
//        if (requestUrl.indexOf("?") != -1){
//            requestUrl =requestUrl.substring(0, requestUrl.indexOf("?")); // 废弃 去除问号及其后面的内容
//        }
        String requestUri =filterInvocation.getRequest().getRequestURI(); // 请求uri 已经去掉了问号及其后面的内容

        /**
         * Notes:
         * requestUrl 分为3类
         * 1.无需认证的接口，比如登录或者注册接口
         * 2.需要认证，但由于每个用户都应该有此类权限，需直接放行的接口，比如获取个人信息的接口（严格来说这是偷懒的做法）
         * 3.需要认证，且需要权限的接口
         * Author: 涛声依旧 likeboat@163.com
         * Time: 2023/3/13 21:51
         **/

        // 第1类
        if (requestUri.equals("/api/configs/getConfigBySymbol")||
                requestUri.equals("/api/refreshToken")||
                requestUri.equals("/api/wechat/jscode2openid")||
                requestUri.equals("/api/dicts/getDictByType")||
                requestUri.equals("/api/dicts/getDictTypes")||
                requestUri.equals("/api/wechat/wechatLogin")) {
            return SecurityConfig.createList("ROLE_LOGIN");
        }

        // 第2类
        if (requestUri.equals("/api/getUserInfo")
                || requestUri.equals("/api/getMenuList")
                || requestUri.equals("/api/getPermCode")) {
            return SecurityConfig.createList("ROLE_EVERYONE");
        }

        // 第3类 ,判定需要的角色
        String method = filterInvocation.getHttpRequest().getMethod(); // 请求的方法
        List<Permission> allPermission = permissionService.getAllPermissions();
        List<String> roleArr = new ArrayList<String>();
        for (Permission permission : allPermission) {
            if(antPathMatcher.match(permission.getRequestUrl(),requestUri)){ // 先判断URL路径是否符合
                // TODO 目前ANY的设定还没体现出来，没完全测试过，前端角色修改也存在问题
                if ("ANY".equals(permission.getRequestMethod())
                        || method.equals(permission.getRequestMethod())){  // 再判断方法是否符合
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
        // 均不满足时，必须拥有root权限才能访问
        return SecurityConfig.createList("ROLE_root");
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
