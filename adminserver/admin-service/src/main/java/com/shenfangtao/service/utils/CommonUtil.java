package com.shenfangtao.service.utils;

import com.shenfangtao.model.Dept;
import com.shenfangtao.model.Permission;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Notes:
 * Author: 涛声依旧 likeboat@163.com
 * Time: 2023/3/2 21:20
 */
@Component
public class CommonUtil {


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
}
