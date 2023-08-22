package com.sbvadmin.common.service;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.sbvadmin.common.utils.WeChatTemplateMsgBuilderUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

/*
 * Notes:  发送公众号模板消息
 * Author: 涛声依旧 likeboat@163.com
 * Time: 2023/8/22 19:17
 **/

@Component
public class WeChatTemplateMsgSenderService {
    // 微信公众号的appid和appsecret
    @Value("${wechat.oa.app-id}")
    private String APPID;
    @Value("${wechat.oa.app-secret}")
    private String APPSECRET;

    // 发送模板消息的接口
    private final String SEND_TEMPLATE_MSG_URL = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=";

    // 获取access_token
    public String getAccessToken() {
        String accessToken = "";
        try {
            // 获取access_token的接口
            URL url = new URL("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + APPID + "&secret=" + APPSECRET);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.connect();
            InputStream inputStream = conn.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuffer buffer = new StringBuffer();
            String str = null;
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }
            bufferedReader.close();
            inputStreamReader.close();
            inputStream.close();
            conn.disconnect();
            // 解析json获取access_token
//            JSONObject jsonObject = JSONObject.fromObject(buffer.toString());
//            accessToken = jsonObject.getString("access_token");
            Map<String, Object> map = JSONUtil.parseObj(buffer.toString());
            System.out.println(map.get("access_token"));
            accessToken = map.get("access_token").toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return accessToken;
    }

    // 构建模板消息
    public String buildTemplateMsg(String toUser, String templateId, String appid, String url, String pagepath,
                                   String keyword1, String keyword2) {
        JSONObject data = WeChatTemplateMsgBuilderUtil.buildTemplateData(keyword1, keyword2);
        String templateMsg = WeChatTemplateMsgBuilderUtil.buildTemplateMsg(toUser, templateId, appid, pagepath, data);
        System.out.println(templateMsg);
        return templateMsg;
    }

    // 发送模板消息
    public void sendTemplateMsg(String accessToken, String templateMsg) {
        try {
            URL url = new URL(SEND_TEMPLATE_MSG_URL + accessToken);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.connect();
            OutputStream outputStream = conn.getOutputStream();
            outputStream.write(templateMsg.getBytes("utf-8"));
            outputStream.close();
            InputStream inputStream = conn.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuffer buffer = new StringBuffer();
            String str = null;
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }
            bufferedReader.close();
            inputStreamReader.close();
            inputStream.close();
            conn.disconnect();
            System.out.println("发送模板消息结果：" + buffer.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
