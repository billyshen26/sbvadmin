package com.shenfangtao.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shenfangtao.mapper.RoleMapper;
import com.shenfangtao.model.Role;
import com.shenfangtao.model.User;
import com.shenfangtao.service.RoleService;
import com.shenfangtao.service.UserService;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

/**
 * Notes:
 * Author: 涛声依旧 likeboat@163.com
 * Time: 2022/8/31 13:45
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

}
