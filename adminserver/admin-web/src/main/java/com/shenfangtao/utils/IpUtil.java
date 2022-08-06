package com.shenfangtao.utils;

import javax.servlet.http.HttpServletRequest;

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
}
