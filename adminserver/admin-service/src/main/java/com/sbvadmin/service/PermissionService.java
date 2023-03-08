package com.sbvadmin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sbvadmin.model.Permission;
import org.springframework.cache.annotation.CacheConfig;

import java.util.List;
import java.util.Map;

/**
 * Notes:
 * Author: 涛声依旧 likeboat@163.com
 * Time: 2022/7/26 16:55
 */
@CacheConfig(cacheNames = "permission_cache")
public interface PermissionService extends IService<Permission> {
//    @Cacheable(key = "#root.methodName")
    public List<Permission> getAllPermissions();

    /**
     * Notes:  获取当前用户id的所有菜单
     * @param: [id]
     * @return: java.util.List<java.util.Map>
     * Author: 涛声依旧 likeboat@163.com
     * Time: 2022/9/9 16:30
     **/
    public List<Map<String, Object>> getMenusByUid(Long id);

    /**
     * Notes:  获取当前用户id的所有按钮
     * @param: [id]
     * @return: java.util.List<java.util.Map>
     * Author: 涛声依旧 likeboat@163.com
     * Time: 2022/9/9 16:30
     **/
    public List<String> getCodesByUid(Long id);

    /**
     * Notes:  获取当前用户id的所有权限，简单来说权限分为菜单和按钮两种
     * @param: [id]
     * @return: java.util.List<com.sbvadmin.model.Permission>
     * Author: 涛声依旧 likeboat@163.com
     * Time: 2022/9/9 16:54
     **/
//    @Cacheable(key = "#id")
    public List<Permission> getPermissionsByUid(Long id);

    /**
     * Notes:  获得树型结构的权限点
     * @param: []
     * @return: java.util.List<java.util.Map>
     * Author: 涛声依旧 likeboat@163.com
     * Time: 2022/9/9 16:15
     **/
//    @Cacheable(key = "#root.methodName")
    public List<Map<String, Object>> getAllPermissionsAsTree();


}
