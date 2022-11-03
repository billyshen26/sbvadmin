package com.shenfangtao.utils;

import com.alibaba.druid.support.json.JSONUtils;
import com.shenfangtao.model.Log;
import com.shenfangtao.model.User;
import com.shenfangtao.service.impl.LogServiceImpl;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootVersion;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Notes: 日志切面类
 * Author: 涛声依旧 likeboat@163.com
 * Time: 2022/8/1 22:00
 */
@Aspect
@Component
public class SbvLogAspect {

    // 统计请求的处理时间
    ThreadLocal<Long> takeUpTime = new ThreadLocal<>();

    @Autowired
    LogServiceImpl logService;

    // 被增强类中的被增强的方法
    @Pointcut(value = "@annotation(com.shenfangtao.utils.SbvLog)")
    public void logAnnotation(){
        System.out.println("pointCut签名");
    }

    // 前置通知
    @Before("logAnnotation()")
    public void doBefore(JoinPoint joinPoint) throws Throwable{
        System.out.println("doBefore");
        takeUpTime.set(System.currentTimeMillis());
    }

    // 返回通知
    @AfterReturning(returning = "ret", pointcut = "logAnnotation()")
    public void doAfterReturning(JoinPoint joinPoint, Object ret) throws Throwable{
        System.out.println("doAfterReturning");
        // 获取RequestAttributes
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        // 从获取RequestAttributes中获取HttpServletRequest的信息
        HttpServletRequest request = (HttpServletRequest) requestAttributes.resolveReference(RequestAttributes.REFERENCE_REQUEST);

        Log log = Log.builder().build();
        // 从切面织入点处通过反射机制获取织入点处的方法
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        // 获取切入点所在的方法
        Method method = signature.getMethod();
        // 获取请求的类名
        String className = joinPoint.getTarget().getClass().getName();
        // 获取操作描述
        SbvLog sbvLog = method.getAnnotation(SbvLog.class);
        if (Objects.nonNull(sbvLog)){
            log.setDescription(sbvLog.desc());
        }

        // 请求信息
        log.setMethod(className + "." + method.getName());
        log.setReqParam(JSONUtils.toJSONString(converMap(request.getParameterMap())));
        log.setUri(request.getRequestURI());
        log.setIp(IpUtil.getIpRequest(request));

        // 用户信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long uid = (Long) authentication.getDetails();
        log.setUid(uid);
        log.setUsername(authentication.getName());

        // 时间信息
        log.setCreatedAt(LocalDateTime.now());
        log.setUpdatedAt(LocalDateTime.now());
        String version = SpringBootVersion.getVersion();
        System.out.println(version);
        String implementationVersion = SpringApplication.class.getPackage().getImplementationVersion();

        System.out.println(implementationVersion);

        log.setVersion(implementationVersion);
        log.setTakeUpTime(System.currentTimeMillis() - takeUpTime.get());

        log.setLevel(Log.ACTION_LEVEL);

        logService.save(log);
    }

    // 异常通知
    @AfterThrowing(throwing = "ex", pointcut = "logAnnotation()")
    public void doAfterThrowing(JoinPoint joinPoint, Exception ex){
        System.out.println("doAfterThrowing");
    }

    // 后置通知
    @After("logAnnotation()")
    public void doAfter(JoinPoint joinPoint){
        System.out.println("doAfter");
    }


    /**
     * Notes:  转换request 请求参数
     * @param: [paramMap]
     * @return: java.util.Map<java.lang.String,java.lang.String>
     * Author: 涛声依旧 likeboat@163.com
     * Time: 2022/8/3 21:08
     **/
    public Map<String, String> converMap(Map<String, String[]> paramMap) {
        Map<String, String> rtnMap = new HashMap<String, String>();
        for (String key : paramMap.keySet()) {
            rtnMap.put(key, paramMap.get(key)[0]);
        }
        return rtnMap;
    }
}
