package com.shenfangtao.service.utils;

import com.shenfangtao.model.Permission;
import com.shenfangtao.model.User;
import com.shenfangtao.service.impl.PermissionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;

/**
 * Notes: 权限管理相关工具方法
 * Author: 涛声依旧 likeboat@163.com
 * Time: 2022/9/9 10:42
 */
@Component
public class AuthUtil {
    /**
     * Notes:  递归查找叶子节点，构建菜单
     * @param: [id, permissionList]
     * @return: java.util.List<java.util.Map>
     * Author: 涛声依旧 likeboat@163.com
     * Time: 2022/9/8 09:23
     **/
    public static List<Map<String, Object>> findChildren(Long id, List<Permission> permissionList){
        List<Map<String, Object>> children = new ArrayList<Map<String, Object>>();
        for (Permission permission : permissionList) {
            if(permission.getPid().equals(id)){
                Map<String, Object> menuChildMap = new HashMap<>();
                menuChildMap.put("id",permission.getId());
                menuChildMap.put("pid",permission.getPid());
                menuChildMap.put("path",permission.getPath());
                menuChildMap.put("name",permission.getName());
                menuChildMap.put("component",permission.getComponent());
                menuChildMap.put("createTime",permission.getCreatedAt());
                menuChildMap.put("orderNo",permission.getOrderNo());
                menuChildMap.put("status",permission.getStatus());
                menuChildMap.put("type",permission.getType());
                menuChildMap.put("icon",permission.getIcon());
                menuChildMap.put("showFlag",permission.getShowFlag());
                menuChildMap.put("title",permission.getTitle());
                menuChildMap.put("requestUrl",permission.getRequestUrl());
                menuChildMap.put("requestMethod",permission.getRequestMethod());

                Map<String, Object> metaChildMap = new HashMap<>();
                metaChildMap.put("title",permission.getTitle());
                metaChildMap.put("icon",permission.getIcon());

                menuChildMap.put("meta",metaChildMap);
                menuChildMap.put("children",findChildren(permission.getId(),permissionList));
                children.add(menuChildMap);
            }
        }
        // 根据orderNo排序 https://www.cnblogs.com/mr-wuxiansheng/p/7768491.html
        Collections.sort(children, new Comparator<Map<String, Object>>() {
            public int compare(Map<String, Object> o1, Map<String, Object> o2) {
                Integer name1 = Integer.valueOf(o1.get("orderNo").toString()) ;//name1是从你list里面拿出来的一个
                Integer name2 = Integer.valueOf(o2.get("orderNo").toString()) ; //name1是从你list里面拿出来的第二个name
                return name1.compareTo(name2);
            }
        });

        return children;
    }

    /**
     * Notes:  递归找到某个权限的所有父节点，协助生成菜单结构
     * @param: [permission, allPermissionList, fathers]
     * @return: void
     * Author: 涛声依旧 likeboat@163.com
     * Time: 2022/9/19 16:10
     **/
    public static void findFathers(Permission permission, List<Permission> allPermissionList , List<Permission> fathers){
        if (permission.getId() != 0L){
            for (Permission permission1 : allPermissionList) {
                if (permission.getId().equals(permission1.getId())){
                    if (!fathers.contains(permission1)){
                        fathers.add(permission1); // 加入自身
                    }
                }
                if (permission1.getId().equals(permission.getPid())){
                    if (!fathers.contains(permission1)){
                        fathers.add(permission1); // 加入父亲
                    }
                    findFathers(permission1,allPermissionList,fathers); // 继续寻找父亲的父亲
                }
            }
        }
    }

    /**
     * Notes:  权限点规则，针对对按钮
     * @param: [permission]
     * @return: java.lang.String
     * Author: 涛声依旧 likeboat@163.com
     * Time: 2022/9/9 17:38
     **/
    public static String  genPermissionRule(Permission permission){
        return permission.getRequestUrl() +"|"+permission.getRequestMethod();
    }

}
