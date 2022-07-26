package com.shenfangtao.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shenfangtao.model.Permission;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

/**
 * Notes:
 * Author: 涛声依旧 likeboat@163.com
 * Time: 2022/7/26 16:55
 */
@CacheConfig(cacheNames = "permission_cache")
public interface PermissionService extends IService<Permission> {
    @Cacheable(key = "#root.methodName")
    public List<Permission> getAllPermissions();
}
