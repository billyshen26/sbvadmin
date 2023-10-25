package com.sbvadmin.utils;

import com.alibaba.druid.support.json.JSONUtils;
import com.sbvadmin.model.Log;
import com.sbvadmin.model.User;
import com.sbvadmin.service.impl.LogServiceImpl;
import com.sbvadmin.service.impl.UserServiceImpl;
import com.sbvadmin.service.utils.CommonUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
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

    @Value("${application.version}")
    private String version;

    @Autowired
    UserServiceImpl userService;

    // 被增强类中的被增强的方法
    @Pointcut(value = "@annotation(com.sbvadmin.utils.SbvLog)")
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

//        Log log = Log.builder().build();
        Log log = new Log();
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
        String ip = IpUtil.getIpRequest(request);
        log.setIp(ip);

        // 用户信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication.getPrincipal().equals("anonymousUser")){
            log.setUid(1L);
            log.setUsername("匿名");
            log.setDid(1L);
        }else{
            Long uid = (Long) authentication.getDetails();
            log.setUid(uid);
            log.setUsername(authentication.getName());
            User user = CommonUtil.getOwnUser();
            log.setDid(user.getLoginDeptId());

            // 更新最近活跃时间
            User updateUser = new User(); // 用一个新的对象更新，防止更新掉密码等其他信息
            updateUser.setId(user.getId());
            updateUser.setLastLoginAt(LocalDateTime.now());
            updateUser.setLastLoginIp(ip);
            userService.updateById(updateUser);
        }
        // 时间信息
        log.setCreatedAt(LocalDateTime.now());
        log.setUpdatedAt(LocalDateTime.now());
        log.setVersion(version);
        log.setTakeUpTime((int)(System.currentTimeMillis() - takeUpTime.get()));
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
