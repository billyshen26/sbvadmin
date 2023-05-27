package com.sbvadmin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sbvadmin.mapper.RoleMapper;
import com.sbvadmin.model.Dept;
import com.sbvadmin.mapper.DeptMapper;
import com.sbvadmin.model.Role;
import com.sbvadmin.model.User;
import com.sbvadmin.service.IDeptService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sbvadmin.service.utils.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
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
            deptList = this.list(queryWrapper);
        }
        List<Dept> treeDepts = CommonUtil.findChildren(0L,deptList);
        return treeDepts;
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
        super.save(entity);

        // 2. 新增管理者角色
        Role role =new Role();
        role.setDid(entity.getId());
        role.setMenu(Arrays.asList(1L,4L));
        role.setNameZh(entity.getName() + "管理员");
        role.setName("ROLE_" + "admin_" + entity.getId());
        role.setDescription(entity.getName() + "默认管理员角色");
        roleMapper.insert(role);

        // 3. 新增默认配置 TODO

        return true;
    }
}
