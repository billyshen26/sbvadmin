package com.sbvadmin.common.utils;

import cn.hutool.json.JSONObject;

/**
 * Notes: 构建公众号模板消息
 * Author: 涛声依旧 likeboat@163.com
 * Time: 2023/7/24 10:11
 */
public class WeChatTemplateMsgBuilderUtil {

    /**
     * 构建模板消息
     * @param toUser 接收者openid
     * @param templateId 模板ID
     * @param data 模板数据
     * @return JSON格式的模板消息
     */
    public static String buildTemplateMsg(String toUser, String templateId, String appid, String pagepath,JSONObject data) {
        JSONObject json = new JSONObject();
        json.set("touser", toUser);
        json.set("template_id", templateId);
        JSONObject miniprogramObj = new JSONObject();
        miniprogramObj.set("appid", appid);
        miniprogramObj.set("pagepath", pagepath);
        json.set("miniprogram", miniprogramObj);
        json.set("data", data);
        return json.toString();
    }

    /**
     * 构建模板数据,仅针对有两个关键字的模板 TODO
     * @param keyword1 关键字1
     * @param keyword2 关键字2
     * @return JSON格式的模板数据
     */
    public static JSONObject buildTemplateData(String keyword1, String keyword2) {
        JSONObject data = new JSONObject();
        JSONObject keyword1Obj = new JSONObject();
        keyword1Obj.set("value", keyword1);
        data.set("keyword1", keyword1Obj);
        JSONObject keyword2Obj = new JSONObject();
        keyword2Obj.set("value", keyword2);
        data.set("keyword2", keyword2Obj);
        return data;
    }
}
