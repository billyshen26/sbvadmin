package com.sbvadmin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sbvadmin.model.Role;
import com.sbvadmin.model.User;

import java.util.List;

/**
 * Notes:
 * Author: 涛声依旧 likeboat@163.com
 * Time: 2022/7/13 10:46
 */

public interface UserService extends IService<User> {
    public List<User> getUsersWithRoles(Long did);

    public List<Role> getUserRolesByUid(Long id);

}
