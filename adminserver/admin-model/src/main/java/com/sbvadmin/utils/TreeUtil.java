package com.sbvadmin.utils;

import com.sbvadmin.model.TreeModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Notes:
 * Author: 涛声依旧 likeboat@163.com
 * Time: 2023/8/10 11:09
 */
public class TreeUtil<T extends TreeModel> {
    /**
     * Notes:  根据id和pid的设计构建生成tree结构
     * @param: [id, objectList]
     * @return: java.util.List<Dept>
     * Author: 涛声依旧 likeboat@163.com
     * Time: 2023/8/10 11:05
     **/
    public List<T> findChildren(Long id, List<T> objectList){
        List<T> children = new ArrayList<T>();
        for (T object : objectList) {
            if(object.getPid().equals(id)){
                object.setChildren(findChildren(object.getId(),objectList));
                children.add(object);
            }
        }
        // 根据orderNo排序 https://www.cnblogs.com/mr-wuxiansheng/p/7768491.html
        Collections.sort(children, new Comparator<T>() {
            public int compare(T o1, T o2) {
                return o1.getOrderNo().compareTo(o2.getOrderNo());
            }
        });
        return children;
    }
}
