package com.sbvadmin.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sbvadmin.utils.SbvLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Notes:
 * Author: 涛声依旧 likeboat@163.com
 * Time: 2022/11/16 21:13
 */
public class BaseController<S extends IService<T>, T> {

    @Autowired
    protected S itemService;

    public S getItemService() {
        return this.itemService;
    }

    @GetMapping("")
    public IPage<T> getItems(@RequestParam(value="id" ,required=false) Long id,
                             @RequestParam(value="createdAt[]" ,required=false) String[] createdAt,
                             @RequestParam(value="field" ,required=false) String field,
                             @RequestParam(value="order" ,required=false) String order,
                             @RequestParam(value="page" ,required=false) Integer page,
                             @RequestParam(value="pageSize" ,required=false) Integer pageSize){
        QueryWrapper<T> queryWrapper = new QueryWrapper<>();
        if (id != null) // id精准搜索
            queryWrapper.eq("id",id);
        if (createdAt != null) // 创建日期范围搜索
            queryWrapper.between("created_at",createdAt[0],createdAt[1]);

        if (page == null){ // 如果未提供分页信息，则默认读取10000行数据
            page = 1;
            pageSize = 10000;
        }
        Page<T> itemPage = new Page<>(page,pageSize);
        if (field != null){ // 排序
            if (order.equals("ascend"))
                itemPage.addOrder(OrderItem.asc(field));
            else
                itemPage.addOrder(OrderItem.desc(field));
        }
        IPage<T> iPage = this.getItemService().page(itemPage, queryWrapper);
        return iPage;
//        return this.getItemService().list();
    }

    @GetMapping("/{id}")
    @SbvLog(desc = "获取详情")
    public T getItem(@PathVariable Long id) {
        return this.getItemService().getById(id);
    }

    @DeleteMapping("/{id}")
    @SbvLog(desc = "删除")
    public Object delItem(@PathVariable Long id) {
        if (this.getItemService().removeById(id))
            return "删除成功!";
        return "删除失败!";
    }

    @PutMapping("/{id}")
    @SbvLog(desc = "修改")
    public Object editItem(@RequestBody T item, @PathVariable Long id) {
        if (this.getItemService().updateById(item))
            return "修改成功!";
        return "修改失败!";
    }

    @PostMapping("")
    @SbvLog(desc = "新增")
    public Object addItem(@RequestBody @Valid T item){
        if (this.getItemService().save(item))
            return "新增成功!";
        return "新增失败!";
    }
}
