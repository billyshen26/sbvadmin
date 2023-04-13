package com.sbvadmin.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.sbvadmin.model.Log;
import com.sbvadmin.service.impl.LogServiceImpl;

/**
 * 日志 前端控制器
 *
 * @author billy
 * @since 2022-12-08
 */
@RestController
@RequestMapping("/api/logs")
public class LogController extends BaseController<LogServiceImpl, Log> {
    public LogController(){
        this.tableName = "sys_log";
    }

}

