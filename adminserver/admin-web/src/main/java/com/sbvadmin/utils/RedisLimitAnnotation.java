package com.sbvadmin.utils;

import java.lang.annotation.*;

/**
 * Notes: 限流注解 https://blog.csdn.net/qq_41482600/article/details/131438844
 * Author: 涛声依旧 likeboat@163.com
 * Time: 2023/10/10 10:32
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface RedisLimitAnnotation {

    /**
     * key
     */
    String key() default "";
    /**
     * Key的前缀
     */
    String prefix() default "";
    /**
     * 一定时间内最多访问次数
     */
    int count();
    /**
     * 给定的时间范围 单位(秒)
     */
    int period();
}