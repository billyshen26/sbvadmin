package com.shenfangtao.admin.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Notes:
 * Author: 涛声依旧 likeboat@163.com
 * Time: 2022/7/11 10:07
 */
@RestController
public class HelloController {
    @GetMapping("/hello")
    public String hello(){
        return "hello sbvadmin!";
    }
}
