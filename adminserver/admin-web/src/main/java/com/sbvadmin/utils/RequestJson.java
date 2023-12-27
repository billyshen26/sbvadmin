package com.sbvadmin.utils;

import java.lang.annotation.*;

/**
 * Notes:  get 请求参数使用该注解就会使用自定义的参数解析器来解析复杂的参数
 * Author: 涛声依旧 likeboat@163.com
 * Time: 2023/12/27 11:31
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequestJson {
}

