package com.sbvadmin.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * Notes: MybatisPlus-常用字段默认值自动填充 https://blog.csdn.net/xiaoyuan_27/article/details/121944789
 * Author: 涛声依旧 likeboat@163.com
 * Time: 2023/3/22 15:57
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        this.fillStrategy(metaObject, "createdAt", LocalDateTime.now());
        this.fillStrategy(metaObject, "updatedAt", LocalDateTime.now());
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.fillStrategy(metaObject, "updatedAt", LocalDateTime.now());
    }
}
