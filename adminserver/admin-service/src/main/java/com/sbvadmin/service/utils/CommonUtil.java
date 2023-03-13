package com.sbvadmin.service.utils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sbvadmin.model.Config;
import com.sbvadmin.model.Dept;
import com.sbvadmin.model.Dict;
import com.sbvadmin.service.impl.ConfigServiceImpl;
import com.sbvadmin.service.impl.DictServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.*;

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


    // 解决静态方法使用Spirng注入空指针问题 https://www.jianshu.com/p/94da6fed473f
    @Autowired
    public CommonUtil(Environment environment, ConfigServiceImpl configService, DictServiceImpl dictService){
        CommonUtil.environment = environment;
        CommonUtil.configService = configService;
        CommonUtil.dictService = dictService;
    }

    /**
     * Notes:  找到子部门，TODO 可以优化后复用
     * @param: [id, objectList]
     * @return: java.util.List<com.sbvadmin.model.Dept>
     * Author: 涛声依旧 likeboat@163.com
     * Time: 2023/3/2 21:26
     **/
    public static List<Dept> findChildren(Long id, List<Dept> objectList){
        List<Dept> children = new ArrayList<Dept>();
        for (Dept object : objectList) {
            if(object.getPid().equals(id)){
                object.setChildren(findChildren(object.getId(),objectList));
                children.add(object);
            }
        }
        // 根据orderNo排序 https://www.cnblogs.com/mr-wuxiansheng/p/7768491.html
        Collections.sort(children, new Comparator<Dept>() {
            public int compare(Dept o1, Dept o2) {
                return o1.getOrderNo().compareTo(o2.getOrderNo());
            }
        });
        return children;
    }

    /*
     * Notes:  返回合适的头像地址
     * @param: [avatar]
     * @return: java.lang.String
     * Author: 涛声依旧 likeboat@163.com
     * Time: 2023/3/5 16:29
     **/
    public static String getAvatarUrl(String avatar){
        String host = environment.getProperty("server.host");
        String port = environment.getProperty("server.port");
        if (!avatar.contains("http")) {
            return  host + ":" + port + File.separator + avatar; // 补充域名和端口
        }
        return avatar;
    }



    /**
     * Notes:  获取配置项
     * @param: [symbol]
     * @return: java.lang.String
     * Author: 涛声依旧 likeboat@163.com
     * Time: 2023/3/9 09:32
     **/
    public static String getConfigBySymbol(String symbol){
        QueryWrapper<Config> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("symbol", symbol);
        Config config = configService.getOne(queryWrapper);
        if (config != null)
            return config.getValue();
        else
            return Config.defaultConfig;
    }


    /**
     * Notes:  获取字典列表
     * @param: [symbol]
     * @return: java.util.List<com.sbvadmin.model.Dict>
     * Author: 涛声依旧 likeboat@163.com
     * Time: 2023/3/13 15:08
     **/
    public static List<Dict> getDictByType(String type){
        QueryWrapper<Dict> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("type", type);
        List<Dict> dictList = dictService.list(queryWrapper);
        return dictList;
//        if (config != null)
//            return config.getValue();
//        else
//            return Config.defaultConfig;
    }
}
