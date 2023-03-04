package com.shenfangtao.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shenfangtao.mapper.UserMapper;
import com.shenfangtao.model.Role;
import com.shenfangtao.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
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
