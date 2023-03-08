package com.sbvadmin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sbvadmin.mapper.UserDeptMapper;
import com.sbvadmin.mapper.UserMapper;
import com.sbvadmin.mapper.UserRoleMapper;
import com.sbvadmin.model.Role;
import com.sbvadmin.model.User;
import com.sbvadmin.model.UserDept;
import com.sbvadmin.model.UserRole;
import com.sbvadmin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Notes:
 * Author: 涛声依旧 likeboat@163.com
 * Time: 2022/7/13 17:53
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService, UserDetailsService {
    @Autowired
    UserMapper userMapper;

    @Autowired
    UserRoleMapper userRoleMapper;

    @Autowired
    UserDeptMapper userDeptMapper;



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",username);
        User user = userMapper.selectOne(queryWrapper);
        if (user == null){
            throw new UsernameNotFoundException("账户不存在");
        }
        user.setRoles(userMapper.getUserRolesByUid(user.getId()));
        return user;
    }

    /**
     * Notes:  获得所有用户列表
     * @param: []
     * @return: java.util.List<com.sbvadmin.model.User>
     * Author: 涛声依旧 likeboat@163.com
     * Time: 2022/7/18 22:38
     **/
    public List<User> getUsersWithRoles(Long did) {
        return userMapper.getUsersWithRoles(did);
    }


    @Override
    public boolean save(User entity) {
        // 1.新增用户
        super.save(entity);
        // 2.给用户分配角色
        for (Integer roleId : entity.getRoleIds()) {
            UserRole userRole = new UserRole();
            userRole.setRid(roleId.longValue());
            userRole.setUid(entity.getId());
            userRoleMapper.insert(userRole);
        }
        // 3.给用户分配机构
        for (Integer deptId : entity.getDeptIds()) {
            UserDept userDept = new UserDept();
            userDept.setDid(deptId.longValue());
            userDept.setUid(entity.getId());
            userDeptMapper.insert(userDept);
        }
        return true;
    }

    public List<Role> getUserRolesByUid(Long id){
        return userMapper.getUserRolesByUid(id);
    }

}
