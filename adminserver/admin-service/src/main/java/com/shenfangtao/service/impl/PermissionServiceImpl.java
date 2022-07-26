package com.shenfangtao.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shenfangtao.mapper.PermissionMapper;
import com.shenfangtao.model.Permission;
import com.shenfangtao.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Notes:
 * Author: 涛声依旧 likeboat@163.com
 * Time: 2022/7/26 17:19
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {
    @Autowired
    PermissionMapper permissionMapper;

    @Override
    public List<Permission> getAllPermissions() {
        return permissionMapper.getAllPermissions();
    }
}
