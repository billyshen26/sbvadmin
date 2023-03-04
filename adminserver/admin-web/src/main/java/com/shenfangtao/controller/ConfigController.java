package com.shenfangtao.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.shenfangtao.controller.BaseController;
import com.shenfangtao.model.Config;
import com.shenfangtao.service.impl.ConfigServiceImpl;

/**
 * 配置 前端控制器
 *
 * @author billy
 * @since 2023-03-02
 */
@RestController
@RequestMapping("/api/configs")
public class ConfigController extends BaseController<ConfigServiceImpl, Config> {


}

