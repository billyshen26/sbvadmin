package com.sbvadmin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.sbvadmin.model.Dept;
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

    @GetMapping("/getDeptsAsTree")
    public List<Dept> getDeptsAsTree(){
        return deptService.getAllDepts();
    }
}

