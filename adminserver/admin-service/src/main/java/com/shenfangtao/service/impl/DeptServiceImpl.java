package com.shenfangtao.service.impl;

import com.shenfangtao.model.Dept;
import com.shenfangtao.mapper.DeptMapper;
import com.shenfangtao.model.Permission;
import com.shenfangtao.service.IDeptService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shenfangtao.service.utils.AuthUtil;
import com.shenfangtao.service.utils.CommonUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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
}
