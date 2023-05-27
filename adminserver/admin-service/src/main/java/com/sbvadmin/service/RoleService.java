package com.sbvadmin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sbvadmin.model.Role;

import java.util.List;

/**
 * Notes:
 * Author: 涛声依旧 likeboat@163.com
 * Time: 2022/8/31 13:45
 */
public interface RoleService  extends IService<Role> {
    public List<Role> getRolesWithPermissions(Long did, String name, String status);
}
