package com.shenfangtao.controller;

import com.shenfangtao.model.Log;
import com.shenfangtao.service.impl.LogServiceImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.shenfangtao.controller.BaseController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author billy
 * @since 2022-11-17
 */
@RestController
@RequestMapping("/api/logs")
public class LogController extends BaseController<LogServiceImpl, Log> {

}
