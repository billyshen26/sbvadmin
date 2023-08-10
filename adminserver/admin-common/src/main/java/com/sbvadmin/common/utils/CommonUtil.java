package com.sbvadmin.common.utils;

import org.springframework.boot.system.ApplicationHome;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Notes:
 * Author: 涛声依旧 likeboat@163.com
 * Time: 2023/6/20 17:46
 */
public class CommonUtil {

    /**
     * Notes:  获取当前jar的执行路径
     * @param: []
     * @return: java.lang.String
     * Author: 涛声依旧 likeboat@163.com
     * Time: 2023/6/20 17:32
     **/
    public static String getJarPath(Class<?> sourceClass){
        ApplicationHome home = new ApplicationHome(sourceClass);
        File jarFile = null;
        String path = null;
        try{
            jarFile = home.getSource() != null ?  home.getSource() : home.getDir();
            String parent = jarFile.getParent();
            path = jarFile.getParentFile().toString();
        }catch(Exception e){
            e.printStackTrace();
        }
        return path;
    }


}
