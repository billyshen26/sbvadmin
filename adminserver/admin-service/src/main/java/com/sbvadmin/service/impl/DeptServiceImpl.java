package com.sbvadmin.service.impl;

import com.sbvadmin.model.Dept;
import com.sbvadmin.mapper.DeptMapper;
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
    @Override
    public List<Dept> getAllDepts() {
        List<Dept> deptList = this.list();
        List<Dept> treeDepts = CommonUtil.findChildren(0L,deptList);
        return treeDepts;
    }

    @Override
    public boolean removeById(Dept entity) {

        return super.removeById(entity);
    }
}
