package com.sbvadmin.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Notes: 模型基类，设定机构id
 * Author: 涛声依旧 likeboat@163.com
 * Time: 2023/5/27 16:55
 */
@Getter
@Setter
public class BaseModel {
    /**
     * 机构外键
     */
    private Long did;

    // TODO 可以把create_at和update_at加入到这里，同时还要改低代码模板
}
