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

import java.io.Serializable;
import java.util.ArrayList;
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

    @Autowired
    UserRoleServiceImpl userRoleService;

    @Autowired
    UserDeptServiceImpl userDeptService;


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
    public List<User> getUsersWithRoles(Long did,Long id,String name) {
        return userMapper.getUsersWithRoles(did,id,name);
    }


    @Override
    public boolean save(User entity) {
        // 新增用户 TODO 加入事务 和更多判断
        super.save(entity);
        // 给用户分配角色和给用户分配机构
        return updateUserRoleAndDept(entity);
    }

    @Override
    public boolean updateById(User entity) {
        // 修改用户 TODO 加入事务 和更多判断
        super.updateById(entity);
        // 给用户分配角色和给用户分配机构
        return updateUserRoleAndDept(entity);
    }
    public List<Role> getUserRolesByUid(Long id){
        return userMapper.getUserRolesByUid(id);
    }

    /*
     * Notes:  修改用户角色关系和用户机构关系
     * @param: [user]
     * @return: boolean
     * Author: 涛声依旧 likeboat@163.com
     * Time: 2023/3/30 15:46
     **/
    private boolean updateUserRoleAndDept(User user){
        // 修改角色
        QueryWrapper userRoleWrapper = new QueryWrapper<>();
        userRoleWrapper.eq("uid",user.getId());
        userRoleService.remove(userRoleWrapper); //先删除之前的分配关系
        List<UserRole> userRoles = new ArrayList<>();
        for (Long roleId : user.getRoleIds()) {
            UserRole userRole = new UserRole();
            userRole.setRid(roleId);
            userRole.setUid(user.getId());
            userRoles.add(userRole);
        }
        userRoleService.saveBatch(userRoles);

        // 修改部门
        QueryWrapper userDeptWrapper = new QueryWrapper<>();
        userDeptWrapper.eq("uid",user.getId());
        userDeptService.remove(userDeptWrapper); //先删除之前的分配关系
        List<UserDept> userDepts = new ArrayList<>();
        for (Long deptId : user.getDeptIds()) {
            UserDept userDept = new UserDept();
            userDept.setDid(deptId);
            userDept.setUid(user.getId());
            userDepts.add(userDept);
        }
        return userDeptService.saveBatch(userDepts);
    }

    /**
     * Notes:  同时删除和用户相关的关系
     * @param: [id]
     * @return: boolean
     * Author: 涛声依旧 likeboat@163.com
     * Time: 2023/4/12 10:07
     **/
    @Override
    public boolean removeById(Serializable id) {
        // 删除用户和角色关系
        QueryWrapper userRoleWrapper = new QueryWrapper<>();
        userRoleWrapper.eq("uid",id);
        userRoleService.remove(userRoleWrapper);

        // 删除用户和部门关系
        QueryWrapper userDeptWrapper = new QueryWrapper<>();
        userDeptWrapper.eq("uid",id);
        userDeptService.remove(userDeptWrapper);

        // 删除用户
        return super.removeById(id);
    }
}
