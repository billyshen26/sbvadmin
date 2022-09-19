package com.shenfangtao.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shenfangtao.model.Role;
import com.shenfangtao.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Notes:
 * Author: 涛声依旧 likeboat@163.com
 * Time: 2022/8/31 13:45
 */
public interface RoleService  extends IService<Role> {
    public List<Role> getRolesWithPermissions();
}
