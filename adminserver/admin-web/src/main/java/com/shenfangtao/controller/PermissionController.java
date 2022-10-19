package com.shenfangtao.controller;

import com.shenfangtao.model.ErrorCode;
import com.shenfangtao.model.Permission;
import com.shenfangtao.model.ResultFormat;
import com.shenfangtao.model.User;
import com.shenfangtao.service.impl.PermissionServiceImpl;
import com.shenfangtao.service.impl.UserServiceImpl;
import com.shenfangtao.utils.SbvLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigInteger;
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

    @GetMapping("")
    public List<Map<String, Object>> getPermissions(){
        return permissionService.getAllPermissionsAsTree();
    }

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
        Long[] preDefinedPermissons = {1L,2L,3L,4L,5L,6L,7L};
        if (Arrays.asList(preDefinedPermissons).contains(id)){
            return ResultFormat.fail(ErrorCode.PERMISSION_CANT_DELETE.getCode(),ErrorCode.PERMISSION_CANT_DELETE.getMessage());
        }
        return permissionService.removeById(id);
    }
}
