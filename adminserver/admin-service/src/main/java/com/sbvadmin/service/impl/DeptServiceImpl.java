package com.sbvadmin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sbvadmin.model.Dept;
import com.sbvadmin.mapper.DeptMapper;
import com.sbvadmin.model.User;
import com.sbvadmin.service.IDeptService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sbvadmin.service.utils.CommonUtil;
import org.springframework.stereotype.Service;

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
}
