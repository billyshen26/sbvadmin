package com.shenfangtao.service.utils;

import com.shenfangtao.model.Dept;
import com.shenfangtao.model.Permission;
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


    // 解决静态方法使用Spirng注入空指针问题 https://www.jianshu.com/p/94da6fed473f
    @Autowired
    public CommonUtil(Environment environment){
        CommonUtil.environment = environment;
    }

    /**
     * Notes:  找到子部门，TODO 可以优化后复用
     * @param: [id, objectList]
     * @return: java.util.List<com.shenfangtao.model.Dept>
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
}
