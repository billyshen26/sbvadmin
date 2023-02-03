package com.shenfangtao.utils;

import javax.servlet.http.HttpServletRequest;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;

/**
 * Notes:
 * Author: 涛声依旧 likeboat@163.com
 * Time: 2022/8/3 21:49
 */
public class IpUtil {
    /**
     * 获取Ip
     *
     * @param request  请求
     */
    public static String getIpRequest(HttpServletRequest request) {
        String unknown = "unknown";

        String ip0 = request.getHeader("x-forwarded-for");
        String ip = request.getHeader("X-Real-IP");

        if (ip == null || ip.length() == 0 || unknown.equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }

        if (ip == null || ip.length() == 0 || unknown.equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }

        if (ip == null || ip.length() == 0 || unknown.equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }

        if ("127.0.0.1".equals(ip)) {
            ip = "local";
        }

        return ip;
    }

    /**
     * 根据IP地址获取地理位置
     * @param ip
     * @return
     */
    public static String getAddressByIP(String ip) {
        if ("local".equals(ip) || "0:0:0:0:0:0:0:1".equals(ip)) {
            return "局域网，无法获取位置";
        }
        String url = "https://sp0.baidu.com/8aQDcjqpAAV3otqbppnN2DJv/api.php?resource_id=6006&format=json&query=" + ip;
        HttpResponse res = HttpRequest.get(url).execute();
        if (200 != res.getStatus()) {
            return "获取位置失败";
        } else {
            JSONObject resJson = JSONUtil.parseObj(res.body());
            JSONArray resArr = JSONUtil.parseArray(resJson.getStr("data"));
            resJson =  JSONUtil.parseObj("" + resArr.get(0));
            return resJson.getStr("location");
        }
    }
}
