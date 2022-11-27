package com.shenfangtao.generator;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Notes: 前端vue页面低代码生成的模板
 * Author: 涛声依旧 likeboat@163.com
 * Time: 2022/11/26 21:11
 */
@AllArgsConstructor
public enum VueFileEnum {

    LIST_VIEW("view","Index.vue","templates/view/ItemIndex.vue.vm"), // 列表页
    ADD_EDIT_VIEW("view","Modal.vue","templates/view/ItemModal.vue.vm"), //新增修改页
    VIET_DATA("view",".data.ts","templates/view/Item.data.ts.vm"), // view页数据模型
    API("api",".ts","templates/api/Item.ts.vm"),
    MODEL("model","Model.ts","templates/model/ItemModel.ts.vm");

    // 文件夹
    @Getter
    private String dir;

    // 文件名
    @Getter
    private String fileName;

    // 模板
    @Getter
    private String template;
}
