package com.sbvadmin.utils;

import cn.hutool.core.codec.Base64;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import jakarta.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 自定义参数解析器，请求参数使用该注解 {@link RequestJson} 就会使用以下解析方式。
 * 说明：前端将参数序列化成 json，再经过 Base64 编码之后放在 url 上传输。
 * 后端 Base64 解码之后，再通过 json 工具将参数反序列化为对象。
 * 例如：http://localhost:8080/user/page?eyJjdXJyZW50IjoxLCJzaXplIjoxMCwidG90YWwiaG9uZSI6IiJ9fQ==
 *  * Notes:
 *  * Author: 涛声依旧 likeboat@163.com
 *  * Time: 2023/12/27 11:32
 */
@Slf4j
public class HandlerRequestJsonArgumentResolver implements HandlerMethodArgumentResolver {

    /**
     * 该方法判断是否要使用该参数解析器。这里我们就判断 有没有使用 @RequestJson 注解来判断是否使用该解析器
     * 如果返回 true 就运行 resolveArgument 方法来解析参数
     * 如果返回 false 使用其他参数解析器来解析参数，springframework 有很多参数解析器
     */
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(RequestJson.class);
    }


    /**
     * 该方法真正用来解析参数。
     * 先使用 Hutool 的 Base64 工具将 json 字符串解析出来
     * 再使用 Hutool 的 JSONUtil 工具将 json 转成 Bean 对象
     */
    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
        try {
            String json = Base64.decodeStr(request.getQueryString());
            Object toBean = JSONUtil.toBean(json, parameter.getGenericParameterType(),false);
            return toBean;
        } catch (Exception e) {
            log.error("请求参数解析失败，请检查！" + e.getMessage());
            Map<String, Object> params = new HashMap<>(); // TODO
            return params;
//            throw new Exception(e);
        }
    }
}
