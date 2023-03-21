package com.sbvadmin.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sbvadmin.service.utils.CommonUtil;
import com.sbvadmin.utils.SbvLog;
import org.springframework.web.bind.annotation.*;
import com.sbvadmin.model.Dict;
import com.sbvadmin.service.impl.DictServiceImpl;

import javax.validation.Valid;
import java.util.List;

/**
 * 字典 前端控制器
 *
 * @author billy
 * @since 2023-03-02
 */
@RestController
@RequestMapping("/api/dicts")
public class DictController extends BaseController<DictServiceImpl, Dict> {
    /**
     * Notes:  根据类型获取字典列表
     * @param: [symbol]
     * @return: java.util.List<com.sbvadmin.model.Dict>
     * Author: 涛声依旧 likeboat@163.com
     * Time: 2023/3/13 15:22
     **/
    @GetMapping("/getDictByType")
    public List<Dict> getDictByType(@RequestParam(value = "type") String type){
        return CommonUtil.getDictByType(type);
    }

    /**
     * Notes:  获得所有的字典类型，便于下拉赋值
     * @return: java.util.List<com.sbvadmin.model.Dict>
     * Author: 涛声依旧 likeboat@163.com
     * Time: 2023/3/21 11:41
     **/
    @GetMapping("/getDictTypes")
    public List<Dict> getDictTypes(){
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.select("DISTINCT type, type_name");
        return itemService.list(queryWrapper);
    }

//    @Override  直接复现不行 item获取不到值，哪位大神解释下 TODO
//    public Object addItem(Dict item) {
//        // 补充typeName
//        QueryWrapper<Dict> queryWrapper=new QueryWrapper<>();
//        queryWrapper.eq("type", item.getType());
//        Dict dict = itemService.getOne(queryWrapper);
//        item.setTypeName(dict.getTypeName());
//        return super.addItem(item);
//    }
    @PostMapping("")
    @SbvLog(desc = "新增")
    public Object addItem(@RequestBody @Valid Dict item){
        // 补充typeName
        QueryWrapper<Dict> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("type", item.getType());
        queryWrapper.last("limit 1");
        Dict dict = itemService.getOne(queryWrapper);
        item.setTypeName(dict.getTypeName());
        if (itemService.save(item))
            return "新增成功!";
        return "新增失败!";
    }
}

