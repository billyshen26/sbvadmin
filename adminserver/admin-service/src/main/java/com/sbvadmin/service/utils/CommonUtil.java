package com.sbvadmin.service.utils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sbvadmin.utils.TreeUtil;
import com.sbvadmin.model.Config;
import com.sbvadmin.model.Dept;
import com.sbvadmin.model.Dict;
import com.sbvadmin.model.User;
import com.sbvadmin.service.impl.ConfigServiceImpl;
import com.sbvadmin.service.impl.DictServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Notes:
 * Author: 涛声依旧 likeboat@163.com
 * Time: 2023/3/2 21:20
 */
@Component
public class CommonUtil {

    @Autowired
    static
    Environment environment;

    @Autowired
    static
    ConfigServiceImpl configService;

    @Autowired
    static
    DictServiceImpl dictService;


    // TIPS: 解决静态方法使用Spirng注入空指针问题 https://www.jianshu.com/p/94da6fed473f
    @Autowired
    public CommonUtil(Environment environment, ConfigServiceImpl configService, DictServiceImpl dictService){
        CommonUtil.environment = environment;
        CommonUtil.configService = configService;
        CommonUtil.dictService = dictService;
    }


//    /*
//     * Notes:  返回合适的头像地址
//     * @param: [avatar]
//     * @return: java.lang.String
//     * Author: 涛声依旧 likeboat@163.com
//     * Time: 2023/3/5 16:29
//     **/
//    public static String getAvatarUrl(String avatar){
////        String host = environment.getProperty("server.host");
//        String host = getConfigBySymbol("host_ip");
//        String port = environment.getProperty("server.port");
//        if (!avatar.contains("http")) {
//            if (!host.contains("http")){ // ip的情况，这里最好用正则 TODO
//                return  "http://" + host + ":" + port + File.separator + avatar; // 补充协议，域名和端口
//            }else{ // 直接提供域名
//                return  host + File.separator + avatar; // 补充协议，域名和端口
//            }
//        }
//        return avatar;
//    }



//    /**
//     * Notes:  获取配置项
//     * @param: [symbol]
//     * @return: java.lang.String
//     * Author: 涛声依旧 likeboat@163.com
//     * Time: 2023/3/9 09:32
//     **/
////    @Cacheable(value = "config", key = "#root.targetClass + #root.methodName + #root.args")
//    public static String getConfigBySymbol(String symbol){
//        QueryWrapper<Config> queryWrapper=new QueryWrapper<>();
//        queryWrapper.eq("symbol", symbol);
//        Config config = configService.getOne(queryWrapper);
//        if (config != null)
//            return config.getValue();
//        else
//            return Config.defaultConfig;
//    }


    /**
     * Notes:  获取字典列表，以树形结构返回
     * @param: [type]
     * @return: java.util.List<com.sbvadmin.model.Dict>
     * Author: 涛声依旧 likeboat@163.com
     * Time: 2023/3/13 15:08
     **/
    public static List<Dict> getDictByType(String type){
        QueryWrapper<Dict> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("type", type);
        queryWrapper.orderByDesc("order_no");
        List<Dict> dictList = dictService.list(queryWrapper);

        // 构建成TREE
        TreeUtil treeUtil = new TreeUtil();
        List<Dict> dictListTree =  treeUtil.findChildren(0L,dictList);
        return dictListTree;
    }

    /**
     * Notes:  获取字典列表,非树形结构返回
     * @param: [type]
     * @return: java.util.List<com.sbvadmin.model.Dict>
     * Author: 涛声依旧 likeboat@163.com
     * Time: 2024/4/17 15:45
     **/
    public static List<Dict> getDictByTypePlain(String type){
        QueryWrapper<Dict> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("type", type);
        queryWrapper.orderByDesc("order_no");
        List<Dict> dictList = dictService.list(queryWrapper);
        return dictList;
    }


    /*
     * Notes:  获取登录用户的user信息
     * @param: []
     * @return: com.sbvadmin.model.User
     * Author: 涛声依旧 likeboat@163.com
     * Time: 2023/5/27 10:33
     **/
    public static User getOwnUser(){
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getPrincipal().equals("anonymousUser")){
            return null;
        }else{
            User user = (User) authentication.getPrincipal();
            return user;
        }
    }
}
