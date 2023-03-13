package com.sbvadmin.config;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.sbvadmin.model.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerExceptionResolver;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Notes: 捕捉权限异常
 * Author: 涛声依旧 likeboat@163.com
 * Time: 2022/8/28 15:53
 */
@Service
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    @Autowired
    private ObjectMapper objectMapper;

    /** 在Filter中注入HandlerExceptionResolver **/
    @Autowired
    @Qualifier("handlerExceptionResolver")
    private HandlerExceptionResolver resolver;

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
//        response.setContentType("application/json;charset=UTF-8");
//        Map map = new HashMap();
//        map.put("code", "403");
//        map.put("msg", accessDeniedException.getMessage());
//        map.put("data","");
//        response.setContentType("application/json");
//        response.setStatus(HttpServletResponse.SC_OK);
//        response.getWriter().write(objectMapper.writeValueAsString(map));
        resolver.resolveException(request, response, null, new AccessDeniedException(ErrorCode.ACCESS_DENIED.getMessage()));
        return;
    }
}
