package com.sbvadmin.service.utils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sbvadmin.model.Config;
import com.sbvadmin.model.Dict;
import com.sbvadmin.service.impl.ConfigServiceImpl;
import com.sbvadmin.service.impl.DictServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Notes:
 * Author: 涛声依旧 likeboat@163.com
 * Time: 2023/9/7 14:15
 */
@Component
public class CommonService {

    @Autowired
    ConfigServiceImpl configService;

    @Autowired
    Environment environment;

    @Autowired
    DictServiceImpl dictService;
    /**
     * Notes:  获取配置项
     * @param: [symbol]
     * @return: java.lang.String
     * Author: 涛声依旧 likeboat@163.com
     * Time: 2023/3/9 09:32
     **/
    @Cacheable(value="config", key = "#root.args")
    public String getConfigBySymbol(String symbol){
        QueryWrapper<Config> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("symbol", symbol);
        Config config = configService.getOne(queryWrapper);
        if (config != null)
            return config.getValue();
        else
            return Config.defaultConfig;
    }

    /**
     * Notes:获取字典列表,并将其转化为MAP数组,从而方便获取字典名
     * @param: [type]
     * @return: java.util.Map<java.lang.Long,java.lang.String>
     * Author: 涛声依旧 likeboat@163.com
     * Time: 2023/3/23 16:40
     **/
    @Cacheable(value="dict", key = "#root.methodName +'_'+ #root.args")
    public Map<Long,String> convertDictListToMap(String type){
        QueryWrapper<Dict> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("type", type);
        List<Dict> dictList = dictService.list(queryWrapper);
        Map<Long,String> dicMaps =  dictList.stream().collect(Collectors.toMap(Dict::getId, Dict::getLabel));
        return dicMaps;
    }

    /*
     * Notes:  返回合适的头像地址
     * @param: [avatar]
     * @return: java.lang.String
     * Author: 涛声依旧 likeboat@163.com
     * Time: 2023/3/5 16:29
     **/
    @Cacheable(value="avatar",key = "#root.args")
    public String getAvatarUrl(String avatar){
        String host = getConfigBySymbol("host_ip");
        String port = environment.getProperty("server.port");
        if (!avatar.contains("http")) {
            if (!host.contains("http")){ // ip的情况，这里最好用正则 TODO
                return  "http://" + host + ":" + port + File.separator + avatar; // 补充协议，域名和端口
            }else{ // 直接提供域名
                return  host + File.separator + avatar; // 补充协议，域名和端口
            }
        }
        return avatar;
    }
}
