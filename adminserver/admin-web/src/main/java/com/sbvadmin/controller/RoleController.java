package com.sbvadmin.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sbvadmin.model.*;
import com.sbvadmin.service.impl.RoleServiceImpl;
import com.sbvadmin.service.impl.UserRoleServiceImpl;
import com.sbvadmin.utils.SbvLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Notes: 角色管理
 * Author: 涛声依旧 likeboat@163.com
 * Time: 2022/8/31 11:37
 */
@RestController
@RequestMapping("/api/roles")
public class RoleController {
    @Autowired
    RoleServiceImpl roleService;

    @Autowired
    UserRoleServiceImpl userRoleService;

    @GetMapping("")
    public List<Role> getRoles(){
        return roleService.getRolesWithPermissions();
    }

    @PostMapping("")
    @SbvLog(desc = "新增角色")
    public boolean addRole(@RequestBody @Valid Role role){
        return roleService.save(role);
    }

    @SbvLog(desc = "修改角色")
    @PutMapping("/{id}")
    public Object editRole(@RequestBody Role role, @PathVariable Long id) {
        if (id == 1L){
            return ResultFormat.fail(ErrorCode.ROOT_CANT_UPDATE);
        }
        role.setId(id);
        return roleService.updateById(role);
    }

    @SbvLog(desc = "删除角色")
    @DeleteMapping("/{id}")
    public Object delRole(@PathVariable Long id) {
        // 预置的几个角色无法删除
        if (id == 1L || id == 2L || id == 3L){
            return ResultFormat.fail(ErrorCode.PRESET_ROLE_CANT_DELETE);
        }

        // 如果角色已经分配给了某些用户，无法删除
        QueryWrapper<UserRole> userRoleQueryWrapper = new QueryWrapper<>();
        userRoleQueryWrapper.eq("rid",id);
        if (userRoleService.list(userRoleQueryWrapper).size() != 0){
            return ResultFormat.fail(ErrorCode.ROLE_CANT_DELETE);
        }

        // 删除角色和权限之间的关系，以及角色
        return roleService.removeById(id);
    }

}
