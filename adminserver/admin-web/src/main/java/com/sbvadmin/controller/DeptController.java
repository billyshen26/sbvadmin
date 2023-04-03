package com.sbvadmin.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sbvadmin.model.*;
import com.sbvadmin.service.impl.UserDeptServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.sbvadmin.service.impl.DeptServiceImpl;

import java.util.List;

/**
 * 机构管理 前端控制器
 *
 * @author billy
 * @since 2023-03-02
 */
@RestController
@RequestMapping("/api/depts")
public class DeptController extends BaseController<DeptServiceImpl, Dept> {
    @Autowired
    DeptServiceImpl deptService;

    @Autowired
    UserDeptServiceImpl userDeptService;

    @GetMapping("/getDeptsAsTree")
    public List<Dept> getDeptsAsTree(){
        return deptService.getAllDepts();
    }

    @Override
    public Object delItem(Long id) {
        // 如果机构已经分配给了某些用户，无法删除
        QueryWrapper<UserDept> userDeptQueryWrapper = new QueryWrapper<>();
        userDeptQueryWrapper.eq("did",id);
        if (userDeptService.list(userDeptQueryWrapper).size() != 0){
            return ResultFormat.fail(ErrorCode.DEPT_CANT_DELETE);
        }

        return super.delItem(id);
    }
}

