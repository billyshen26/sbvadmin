package com.sbvadmin.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sbvadmin.service.utils.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.sbvadmin.model.Config;
import com.sbvadmin.service.impl.ConfigServiceImpl;

import java.util.Map;

/**
 * 配置 前端控制器
 *
 * @author billy
 * @since 2023-03-02
 */
@RestController
@RequestMapping("/api/configs")
public class ConfigController extends BaseController<ConfigServiceImpl, Config> {
    @Autowired
    ConfigServiceImpl configService;

    /**
     * Notes:  根据标识符获取配置值
     * @param: [symbol]
     * @return: java.lang.String
     * Author: 涛声依旧 likeboat@163.com
     * Time: 2023/3/7 21:00
     **/
    @GetMapping("/getConfigBySymbol")
    public String getConfigBySymbol(@RequestParam(value = "symbol") String symbol){
        return CommonUtil.getConfigBySymbol(symbol);
    }
//    @PostMapping("/getConfigBySymbol")
//    public String getConfigBySymbol(@RequestBody Map<String, Object> params){
//        QueryWrapper<Config> queryWrapper=new QueryWrapper<>();
//        queryWrapper.eq("symbol", (String) params.get("symbol"));
//        Config config = configService.getOne(queryWrapper);
//        if (config != null)
//            return config.getValue();
//        else
//            return "暂未配置";
//    }
}

