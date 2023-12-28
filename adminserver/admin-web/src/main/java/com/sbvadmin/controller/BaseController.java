package com.sbvadmin.controller;

import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.spring.SpringUtil;
import cn.hutool.json.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sbvadmin.model.BaseModel;
import com.sbvadmin.model.Dept;
import com.sbvadmin.model.ResultFormat;
import com.sbvadmin.model.User;
import com.sbvadmin.service.impl.DeptServiceImpl;
import com.sbvadmin.service.utils.CommonUtil;
import com.sbvadmin.utils.RequestJson;
import com.sbvadmin.utils.SbvLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Notes:
 * Author: 涛声依旧 likeboat@163.com
 * Time: 2022/11/16 21:13
 */
public class BaseController<S extends IService<T>, T extends BaseModel> {

    protected static final String LIKE = "like";
    protected static final String EQ = "eq";

    @Autowired
    protected S itemService;

    protected String tableName;
    protected Map<String, Object> condition;
    public S getItemService() {
        return this.itemService;
    }

    @Autowired
    DeptServiceImpl deptService;

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
     * Notes:  各类条件字段
     * @param: []
     * @return: java.lang.String
     * Author: 涛声依旧 likeboat@163.com
     * Time: 2023/6/1 20:37
     **/
    public Map<String, Object> getCondition() {
        return condition;
    }

    /*
     * Notes:  构造函数，初始化搜索条件map等
     * @param: []
     * @return:
     * Author: 涛声依旧 likeboat@163.com
     * Time: 2023/12/27 16:28
     **/
    public BaseController(){
        this.condition = new HashMap<>();
    }

    @GetMapping("")
    public IPage<T> getItems( @RequestJson Map<String, Object> params ){
        QueryWrapper<T> queryWrapper = new QueryWrapper<>();
        // id精准搜索
        if (params.get("id") != null)
            queryWrapper.eq(this.getTableName()+"id",params.get("id"));
        // 创建日期范围搜索
        if (params.get("createdAt") != null){
            JSONArray createdAtArray = (JSONArray) params.get("createdAt");
            queryWrapper.between(this.getTableName()+"created_at",createdAtArray.get(0),createdAtArray.get(1));
        }
        // 自定义搜索
        this.getCondition().forEach((k, v) -> {
            if(params.get(k) != null){
                if (v.equals("like")) queryWrapper.like(k,params.get(k));
                if (v.equals("eq")) queryWrapper.eq(k,params.get(k));
            }
        });

        // 2023-05-27 根据机构id查询，设置数据权限
        // 2023-06-12 修改成本人所拥有的所有did TODO 可能有bug
//        List<Dept> deptList =  deptService.getAllDepts();
        List<Dept> deptList =  deptService.getDeptsByUserId(CommonUtil.getOwnUser().getId());
        List<Long> deptIdList = new ArrayList<>();
        deptList.forEach(item ->{
            deptIdList.add(item.getId());
        });
        queryWrapper.in(this.getTableName()+"did",deptIdList);

        long page = 1;
        long pageSize = 10000;
        if (params.get("page") != null){ // 如果未提供分页信息，则默认读取10000行数据
            page = Long.valueOf((Integer) params.get("page"));
            pageSize = Long.valueOf((Integer) params.get("pageSize"));
        }
        Page<T> itemPage = new Page<>(page,pageSize);
        if (params.get("field") != null){ // 排序
            if (params.get("order").equals("ascend"))
                itemPage.addOrder(OrderItem.asc((String) params.get("field")));
            else
                itemPage.addOrder(OrderItem.desc((String) params.get("field")));
        }
        IPage<T> iPage = this.getItemService().page(itemPage, queryWrapper);

        /**
         * Notes: 在获取items之后，加入一个切面，可以方便定制化处理返回的数据
         * Time: 2023/12/28 11:39
         **/
        try {
            Class<?> clazz = this.getClass();
            Method method = clazz.getMethod("afterGetItems", Long.class);
            if(method != null) { // 判断该方法是否存在
                System.out.println("该方法存在");
                String className = StrUtil.lowerFirst(clazz.getSimpleName());
                iPage = (IPage) method.invoke(SpringUtil.getBean(className), iPage);
            }
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            System.out.println("该方法不存在");
        }

        return iPage;
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
    public Object delItem(@PathVariable Long id) {
        /**
         * Notes: 在删除item 之前，加入一个切面，可以方便定制化处理删除前的工作；比如判断是否有数据关联性
         * Time: 2023/6/12 11:28
         **/
        ResultFormat resultFormat = ResultFormat.success("OK");
        try {
            Class<?> clazz = this.getClass();// 获取类的Class对象
            Method method = clazz.getMethod("beforeDel", Long.class);// 获取方法名为methodName，参数类型为paramType的方法
            if(method != null) { // 判断该方法是否存在
                System.out.println("该方法存在");
                String className = StrUtil.lowerFirst(clazz.getSimpleName());
                resultFormat = (ResultFormat) method.invoke(SpringUtil.getBean(className), id);
            }
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            System.out.println("该方法不存在");
        }
        if (resultFormat.getCode() == 0 ){
            if (this.getItemService().removeById(id))
                return "删除成功!";
            return "删除失败!";
        }else {
            return resultFormat;
        }
    }

    @PutMapping("/{id}")
    @SbvLog(desc = "修改")
    public Object editItem(@RequestBody T item, @PathVariable Long id) {

        /**
         * Notes:  在修改item 之前，加入一个切面，可以方便定制化处理修改前的工作；比如判断是否有数据关联性
         * Time: 2023/12/21 13:32
         **/
        ResultFormat resultFormat = ResultFormat.success("OK");
        try {
            Class<?> clazz = this.getClass();// 获取类的Class对象
            Method method = clazz.getMethod("beforeEdit", item.getClass());// 获取方法名为methodName，参数类型为paramType的方法
            if(method != null) { // 判断该方法是否存在
                System.out.println("该方法存在");
                String className = StrUtil.lowerFirst(clazz.getSimpleName());
                resultFormat = (ResultFormat) method.invoke(SpringUtil.getBean(className), item);
            }
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            System.out.println("该方法不存在");
        }
        if (resultFormat.getCode() == 0 ){
            if (this.getItemService().updateById(item))
                return item;
            return "修改失败!";
        }else {
            return resultFormat;
        }
    }

    @PostMapping("")
    @SbvLog(desc = "新增")
    public Object addItem(@RequestBody @Valid T item){
        /*
         * Notes:  在新增item 之前，加入一个切面，可以方便定制化处理新增前的工作；比如判断是否有数据关联性
         * Time: 2023/8/17 15:41
         **/
        ResultFormat resultFormat = ResultFormat.success("OK");
        try {
            Class<?> clazz = this.getClass();// 获取类的Class对象
            Method method = clazz.getMethod("beforeAdd", item.getClass());// 获取方法名为methodName，参数类型为paramType的方法
            if(method != null) { // 判断该方法是否存在
                System.out.println("该方法存在");
                String className = StrUtil.lowerFirst(clazz.getSimpleName());
                resultFormat = (ResultFormat) method.invoke(SpringUtil.getBean(className), item);
            }
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            System.out.println("该方法不存在");
        }
        if (resultFormat.getCode() == 0 ){
            item.setDid(CommonUtil.getOwnUser().getLoginDeptId()); // 2023-05-27 新增添加机构id，设置数据权限
            if (this.getItemService().save(item))
                return item;
            return "新增失败!";
        }else {
            return resultFormat;
        }
    }
}
