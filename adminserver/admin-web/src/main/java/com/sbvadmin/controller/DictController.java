package com.sbvadmin.controller;

import com.sbvadmin.service.utils.CommonUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.sbvadmin.model.Dict;
import com.sbvadmin.service.impl.DictServiceImpl;

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
}

