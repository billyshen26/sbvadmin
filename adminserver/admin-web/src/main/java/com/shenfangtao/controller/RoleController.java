package com.shenfangtao.controller;

import com.shenfangtao.model.Permission;
import com.shenfangtao.model.Role;
import com.shenfangtao.model.User;
import com.shenfangtao.service.impl.RoleServiceImpl;
import com.shenfangtao.utils.SbvLog;
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

    @GetMapping("")
    public List<Role> getRoles(){
        return roleService.getRolesWithPermissions();
    }

    @PostMapping("")
    @SbvLog(desc = "新增角色")
    public boolean addRole(@RequestBody @Valid Role role){
        return roleService.save(role);
    }

    @PutMapping("/{id}")
    public boolean editRole(@RequestBody Role role, @PathVariable Long id) {
        role.setId(id);
        return roleService.updateById(role);
    }
    @DeleteMapping("/{id}")
    public boolean delRole(@PathVariable Long id) {
        return roleService.removeById(id);
    }

}
