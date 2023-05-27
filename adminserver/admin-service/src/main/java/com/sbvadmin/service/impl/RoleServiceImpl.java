package com.sbvadmin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sbvadmin.mapper.RoleMapper;
import com.sbvadmin.model.Role;
import com.sbvadmin.model.RolePermission;
import com.sbvadmin.model.User;
import com.sbvadmin.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    /*
     * Notes:  补充机构id，获取权限列表，用于菜单管理和新建角色时选择权限
     * @param: [did, name, status]
     * @return: java.util.List<com.sbvadmin.model.Role>
     * Author: 涛声依旧 likeboat@163.com
     * Time: 2023/5/27 20:02
     **/
    @Override
    public List<Role> getRolesWithPermissions(Long did, String name, String status) {
        return roleMapper.getRolesWithPermissions(did, name, status);
    }

    /**
     * Notes:  修改角色，同时修改角色和权限的关系
     * @param: [entity]
     * @return: boolean
     * Author: 涛声依旧 likeboat@163.com
     * Time: 2022/9/17 17:29
     **/
    @Override
    public boolean updateById(Role entity) {
        // 更新该角色的其他信息
        super.updateById(entity);  // TODO 需要进行事务处理
        if(entity.getMenu() == null) return true; // 不修改权限点的情况，比如启用禁用角色
        //更新角色和权限之间的关系
        return updateRolePermission(entity);
    }

    /*
     * Notes:  新增角色，同时修改角色和权限的关系
     * @param: [entity]
     * @return: boolean
     * Author: 涛声依旧 likeboat@163.com
     * Time: 2023/3/30 15:26
     **/
    @Override
    public boolean save(Role entity) {
        // 新增角色
        super.save(entity);
        //更新角色和权限之间的关系
        return updateRolePermission(entity);
    }

    @Override
    public boolean removeById(Role entity) {
        // 删除角色和权限之间的关系
        QueryWrapper<RolePermission> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("rid", entity.getId());
        rolePermissionService.remove(queryWrapper);
        // 删除角色
        return super.removeById(entity);
    }

    /*
     * Notes:  更新角色和权限之间的关系
     * @param: [entity]
     * @return: boolean
     * Author: 涛声依旧 likeboat@163.com
     * Time: 2023/3/30 15:23
     **/
    private boolean updateRolePermission(Role entity){
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
        return rolePermissionService.saveBatch(rolePermissions);
    }
}
