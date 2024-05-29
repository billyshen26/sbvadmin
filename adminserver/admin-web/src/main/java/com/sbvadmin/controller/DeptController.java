package com.sbvadmin.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sbvadmin.model.*;
import com.sbvadmin.service.impl.UserDeptServiceImpl;
import com.sbvadmin.service.utils.CommonUtil;
import com.sbvadmin.utils.SbvLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.sbvadmin.service.impl.DeptServiceImpl;

import jakarta.validation.Valid;
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

    /*
     * Notes:  新增部门
     * @param: [item]
     * @return: java.lang.Object
     * Author: 涛声依旧 likeboat@163.com
     * Time: 2023/5/27 22:12
     **/
    @PostMapping("")
    @SbvLog(desc = "新增")
    public Object addItem(@RequestBody @Valid Dept item){
        if (this.getItemService().save(item))
            return "新增成功!";
        return "新增失败!";
    }

//    @DeleteMapping("/{id}")
//    @SbvLog(desc = "删除")
//    @Override
//    public Object delItem(@PathVariable Long id) {
//        // 如果机构已经分配给了某些用户，无法删除
//        QueryWrapper<UserDept> userDeptQueryWrapper = new QueryWrapper<>();
//        userDeptQueryWrapper.eq("did",id);
//        if (userDeptService.list(userDeptQueryWrapper).size() != 0){
//            return ResultFormat.fail(ErrorCode.DEPT_CANT_DELETE);
//        }
//        return super.delItem(id);
//    }

    public ResultFormat beforeDel(Long id){
        // 如果机构已经分配给了某些用户，无法删除
        QueryWrapper<UserDept> userDeptQueryWrapper = new QueryWrapper<>();
        userDeptQueryWrapper.eq("did",id);
        if (userDeptService.list(userDeptQueryWrapper).size() != 0){
            return ResultFormat.fail(ErrorCode.DEPT_CANT_DELETE);
        }
        return ResultFormat.success("可以删除");
    }
}

