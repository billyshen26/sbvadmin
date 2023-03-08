package com.sbvadmin.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.sbvadmin.model.Dict;
import com.sbvadmin.service.impl.DictServiceImpl;

/**
 * 字典 前端控制器
 *
 * @author billy
 * @since 2023-03-02
 */
@RestController
@RequestMapping("/api/dicts")
public class DictController extends BaseController<DictServiceImpl, Dict> {


}

