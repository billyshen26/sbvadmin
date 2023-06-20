package com.sbvadmin.utils;

import org.springframework.boot.system.ApplicationHome;

import java.io.File;

/**
 * Notes:
 * Author: 涛声依旧 likeboat@163.com
 * Time: 2023/6/20 17:46
 */
public class CommonWebUtil {

    /**
     * Notes:  获取当前jar的执行路径
     * @param: []
     * @return: java.lang.String
     * Author: 涛声依旧 likeboat@163.com
     * Time: 2023/6/20 17:32
     **/
    public static String getJarPath(){
        ApplicationHome home = new ApplicationHome(CommonWebUtil.class);
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
