package com.sbvadmin.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sbvadmin.model.BaseModel;
import com.sbvadmin.model.User;
import com.sbvadmin.service.utils.CommonUtil;
import com.sbvadmin.utils.SbvLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Notes:
 * Author: 涛声依旧 likeboat@163.com
 * Time: 2022/11/16 21:13
 */
@CacheConfig(cacheNames = "cache")
public class BaseController<S extends IService<T>, T extends BaseModel> {

    @Autowired
    protected S itemService;

    protected String tableName;
    protected String likeSearch;
    protected String equalSearch;
    public S getItemService() {
        return this.itemService;
    }

    /*
     * Notes:  格式化表名，用于辅助生成链表查询的时候进行字段的前缀添加
     * @param: []
     * @return: java.lang.String
     * Author: 涛声依旧 likeboat@163.com
     * Time: 2023/4/13 16:58
     **/
    protected String getTableName(){
        if (this.tableName != null){
            return this.tableName + ".";
        }else{
            return "";
        }
    }

    /*
     * Notes:  模糊搜索字段
     * @param: []
     * @return: java.lang.String
     * Author: 涛声依旧 likeboat@163.com
     * Time: 2023/6/1 20:37
     **/
    public String getLikeSearch() {
        return likeSearch;
    }
    /**
     * Notes:  全等搜索字段
     * @param: []
     * @return: java.lang.String
     * Author: 涛声依旧 likeboat@163.com
     * Time: 2023/6/6 15:23
     **/
    public String getEqualSearch() {
        return equalSearch;
    }

    @GetMapping("")
    @Cacheable(key = "#root.targetClass + #root.methodName + #root.args")
    public IPage<T> getItems(@RequestParam(value="id" ,required=false) Long id,
                             @RequestParam(value="createdAt[]" ,required=false) String[] createdAt,
                             @RequestParam(value="likeSearch" ,required=false) String likeSearch,
                             @RequestParam(value="equalSearch" ,required=false) String equalSearch,
                             @RequestParam(value="field" ,required=false) String field,
                             @RequestParam(value="order" ,required=false) String order,
                             @RequestParam(value="page" ,required=false) Integer page,
                             @RequestParam(value="pageSize" ,required=false) Integer pageSize){
        QueryWrapper<T> queryWrapper = new QueryWrapper<>();
        if (id != null) // id精准搜索
            queryWrapper.eq(this.getTableName()+"id",id);
        if (createdAt != null) // 创建日期范围搜索
            queryWrapper.between("created_at",createdAt[0],createdAt[1]);
        if (likeSearch != null) // 自定义模糊内容搜索：比如name等
            queryWrapper.like(this.getLikeSearch(),likeSearch);
        if (equalSearch != null) // 自定义全等内容搜索：比如type等
            queryWrapper.eq(this.getEqualSearch(),equalSearch);

        // 2023-05-27 根据机构id查询，设置数据权限
        User user = CommonUtil.getOwnUser();
        queryWrapper.eq(this.getTableName()+"did",user.getLoginDeptId());

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
        QueryWrapper<T> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(this.getTableName()+"id",id);
        return this.getItemService().getOne(queryWrapper);
    }

    @DeleteMapping("/{id}")
    @SbvLog(desc = "删除")
    @CacheEvict(key = "#root.targetClass + #root.methodName + #root.args", allEntries = true)
    public Object delItem(@PathVariable Long id) {
        if (this.getItemService().removeById(id))
            return "删除成功!";
        return "删除失败!";
    }

    @PutMapping("/{id}")
    @SbvLog(desc = "修改")
    @CacheEvict(key = "#root.targetClass + #root.methodName + #root.args", allEntries = true)
    public Object editItem(@RequestBody T item, @PathVariable Long id) {
        if (this.getItemService().updateById(item))
            return "修改成功!";
        return "修改失败!";
    }

    @PostMapping("")
    @SbvLog(desc = "新增")
    @CacheEvict(key = "#root.targetClass + #root.methodName + #root.args", allEntries = true)
    public Object addItem(@RequestBody @Valid T item){
        item.setDid(CommonUtil.getOwnUser().getLoginDeptId()); // 2023-05-27 新增添加机构id，设置数据权限
        if (this.getItemService().save(item))
            return "新增成功!";
        return "新增失败!";
    }
}
