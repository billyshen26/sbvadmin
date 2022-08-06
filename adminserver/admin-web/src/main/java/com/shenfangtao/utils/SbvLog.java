package com.shenfangtao.utils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Notes: 自定义日志注解
 * Author: 涛声依旧 likeboat@163.com
 * Time: 2022/8/1 21:36
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface SbvLog {
    String desc() default " ";
}
