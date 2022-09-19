package com.shenfangtao.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shenfangtao.mapper.RoleMapper;
import com.shenfangtao.model.Role;
import com.shenfangtao.model.RolePermission;
import com.shenfangtao.model.User;
import com.shenfangtao.service.RoleService;
import com.shenfangtao.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Notes:
 * Author: 涛声依旧 likeboat@163.com
 * Time: 2022/8/31 13:45
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Autowired
    RoleMapper roleMapper;

    @Autowired
    RolePermissionServiceImpl rolePermissionService;

    @Override
    public List<Role> getRolesWithPermissions() {
        return roleMapper.getRolesWithPermissions();
    }

    /**
     * Notes:  同时修改角色和权限的关系
     * @param: [entity]
     * @return: boolean
     * Author: 涛声依旧 likeboat@163.com
     * Time: 2022/9/17 17:29
     **/
    @Override
    public boolean updateById(Role entity) {
        // 1. 删除所有改角色的权限点
        QueryWrapper<RolePermission> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("rid", entity.getId());
        rolePermissionService.remove(queryWrapper);
        // 2. 重新给该角色赋予权限点
        List<RolePermission> rolePermissions = new ArrayList<>();
        for (Long menu : entity.getMenu()) {
            RolePermission rolePermission = new RolePermission();
            rolePermission.setPid(menu);
            rolePermission.setRid(entity.getId());
            rolePermissions.add(rolePermission);
        }
        rolePermissionService.saveBatch(rolePermissions);
        // 更新该角色的其他信息
        return super.updateById(entity);  // TODO 需要进行事务处理
    }
}
