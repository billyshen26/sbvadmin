package com.sbvadmin.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sbvadmin.model.*;
import com.sbvadmin.service.impl.PermissionServiceImpl;
import com.sbvadmin.service.impl.RolePermissionServiceImpl;
import com.sbvadmin.utils.SbvLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Notes:
 * Author: 涛声依旧 likeboat@163.com
 * Time: 2022/9/9 14:20
 */
@RestController
@RequestMapping("/api/permissions")
public class PermissionController {
    @Autowired
    PermissionServiceImpl permissionService;

    @Autowired
    RolePermissionServiceImpl rolePermissionService;

    @GetMapping("")
    public List<Map<String, Object>> getPermissions(){
        return permissionService.getAllPermissionsAsTree();
    }

    @Autowired
    private RedisTemplate redisTemplate;

    @PostMapping("")
    @SbvLog(desc = "新增权限点")
    public boolean addPermission(@RequestBody @Valid Permission permission){
        return permissionService.save(permission);
    }

    @SbvLog(desc = "修改权限点")
    @PutMapping("/{id}")
    public boolean editPermission(@RequestBody Permission permission, @PathVariable Long id) {
        permission.setId(id);
        return permissionService.updateById(permission);
    }
    @SbvLog(desc = "删除权限点")
    @DeleteMapping("/{id}")
    public Object delPermission(@PathVariable Long id) {
        // 预置权限点不能被删除
        Long[] preDefinedPermissons = {1L,2L,3L,4L,5L,6L,7L};
        // TIPS: 数组包含
        if (Arrays.asList(preDefinedPermissons).contains(id)){
            return ResultFormat.fail(ErrorCode.PRESET_PERMISSION_CANT_DELETE);
        }

        // 如果权限已经分配给了某些角色，无法删除
        QueryWrapper<RolePermission> rolePermissionQueryWrapper = new QueryWrapper<>();
        rolePermissionQueryWrapper.eq("pid",id);
        if (rolePermissionService.list(rolePermissionQueryWrapper).size() != 0){
            return ResultFormat.fail(ErrorCode.PERMISSION_CANT_DELETE);
        }

        return permissionService.removeById(id);
    }


    /**
     * Notes:  手动更新所有菜单缓存
     * @param: []
     * @return: java.lang.Object
     * Author: 涛声依旧 likeboat@163.com
     * Time: 2023/9/3 20:50
     **/
    @PostMapping("/refreshPermissions")
    @CacheEvict( allEntries = true)
    public Object refreshPermissions(){
        return "ok";
    }

    /**
     * Notes:  删除所有缓存
     * @param: []
     * @return: java.lang.Object
     * Author: 涛声依旧 likeboat@163.com
     * Time: 2023/9/7 14:56
     **/
    @PostMapping("/refreshAllCache")
    public Object refreshAllCache(){
        //基于SpringBoot框架自动配置的redisTemplate,操作redis缓存
        //获取连接
        RedisConnection connection = redisTemplate.getConnectionFactory().getConnection();
        //清空数据库中的所有数据
        connection.flushDb();
        return "ok";
    }
}
