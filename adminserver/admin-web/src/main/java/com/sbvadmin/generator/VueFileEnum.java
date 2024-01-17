package com.sbvadmin.generator;

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
    DETAIL_VIEW("view","Detail.vue","templates/view/ItemDetail.vue.vm"), //详情页，也用于复杂页面的新增
    ADD_EDIT_VIEW("view","Modal.vue","templates/view/ItemModal.vue.vm"), //新增修改页
    VIET_DATA("view",".data.ts","templates/view/Item.data.ts.vm"), // view页数据模型
    API("api",".ts","templates/api/Item.ts.vm"), // 请求接口
    MODEL("model","Model.ts","templates/model/ItemModel.ts.vm"), // 表模型
    I18N_EN("i18nEN",".json", "templates/i18n/i18n.json.vm"), // i18n 菜单名称 英文

    I18N_ZH("i18nZH",".json", "templates/i18n/i18nZh.json.vm"),// i18n 菜单名称 中文

    MENU_SQL("sql","Menu.sql","templates/sql/menu.sql.vm"),// 生成菜单的sql

    CONTROLLER("controller","Controller.java","templates/controller/controller.java.vm");// 自定义Controller

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
