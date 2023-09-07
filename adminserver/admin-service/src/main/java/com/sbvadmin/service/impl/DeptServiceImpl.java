package com.sbvadmin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sbvadmin.mapper.*;
import com.sbvadmin.model.*;
import com.sbvadmin.service.IDeptService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sbvadmin.service.utils.CommonUtil;
import com.sbvadmin.utils.TreeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 机构管理 服务实现类
 * </p>
 *
 * @author billy
 * @since 2023-03-02
 */
@Service
public class DeptServiceImpl extends ServiceImpl<DeptMapper, Dept> implements IDeptService {

    @Autowired
    RoleMapper roleMapper;

    @Autowired
    RolePermissionMapper rolePermissionMapper;

    @Autowired
    UserMapper userMapper;

    @Autowired
    UserRoleMapper userRoleMapper;

    @Autowired
    UserDeptMapper userDeptMapper;
    /**
     * Notes:  获得自己可以管理的机构，root可获取所有机构
     * @param: []
     * @return: java.util.List<com.sbvadmin.model.Dept>
     * Author: 涛声依旧 likeboat@163.com
     * Time: 2023/5/27 16:03
     **/
    @Override
    public List<Dept> getAllDepts() {
        User user = CommonUtil.getOwnUser();
        List<Dept> deptList = null;
        if (user.getId() == 1L) { // root
            deptList = this.baseMapper.getAllDepts();
        }else {
            QueryWrapper<Dept> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("uid",user.getId());
            queryWrapper.orderByDesc("order_no");
            deptList = this.list(queryWrapper);
        }
//        List<Dept> treeDepts = CommonUtil.findChildren(0L,deptList);
        // 构建成TREE
        TreeUtil treeUtil = new TreeUtil();
        List<Dept> treeDepts =  treeUtil.findChildren(0L,deptList);
        return treeDepts;
    }


    @Override
    public List<Dept> getDeptsByUserId(Long id){
        List<Dept> deptList = null;
        if (id == 1L) { // root
            deptList = this.baseMapper.getAllDepts();
        }else {
            QueryWrapper<Dept> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("uid",id);
            queryWrapper.orderByDesc("order_no");
            deptList = this.list(queryWrapper);
        }
        return deptList;
    }

    @Override
    public boolean removeById(Dept entity) {
        return super.removeById(entity);
    }

    /*
     * Notes:
     * @param: [entity]
     * @return: boolean
     * Author: 涛声依旧 likeboat@163.com
     * Time: 2023/5/27 22:16
     **/
    @Override
    public boolean save(Dept entity) {

        // 1. 新增机构
        boolean saas = false;
        super.save(entity);
        if (saas){ // 如果是SAAS软件的话，应该再创建机构的时候初始化一些信息 TODO
            // 2.1 新增默认管理者角色
            Role role =new Role();
            role.setDid(entity.getId());
            role.setNameZh(entity.getName() + "管理员");
            role.setName("ROLE_" + "admin_" + entity.getId());
            role.setDescription(entity.getName() + "默认管理员角色");
            roleMapper.insert(role);
            // 2.2 新增默认管理者角色-角色和权限的关系
            QueryWrapper<RolePermission> queryWrapper=new QueryWrapper<>();
            queryWrapper.eq("rid", 2L); // 取admin的权限点
            List<RolePermission> adminList = rolePermissionMapper.selectList(queryWrapper);
            for (RolePermission rp : adminList) {
                RolePermission rolePermission = new RolePermission();
                rolePermission.setPid(rp.getPid());
                rolePermission.setRid(role.getId());
                rolePermissionMapper.insert(rolePermission);
            }

            // 3.1 新增默认管理员用户
            User user = new User();
            user.setNickname(entity.getName() + "管理员");
            user.setUsername("admin_" + entity.getId()); // 用户名默认admin_x
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            user.setPassword(passwordEncoder.encode("admin_" + entity.getId())); // 密码同用户名
            user.setHomePath("/dashboard/analysis");
            user.setAvatar("avatar.png");
            userMapper.insert(user);

            // 3.2 新增默认管理员用户-用户和角色关系
            UserRole userRole = new UserRole();
            userRole.setRid(role.getId());
            userRole.setUid(user.getId());
            userRoleMapper.insert(userRole);

            // 3.3 新增默认管理员用户-用户和角色关系
            UserDept userDept = new UserDept();
            userDept.setDid(entity.getId());
            userDept.setUid(user.getId());
            userDeptMapper.insert(userDept);

            // 4. 新增默认配置
        }
        return true;
    }
}
