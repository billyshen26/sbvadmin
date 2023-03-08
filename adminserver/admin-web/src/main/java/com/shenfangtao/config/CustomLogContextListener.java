package com.shenfangtao.config;

/**
 * Notes:
 * Author: 涛声依旧 likeboat@163.com
 * Time: 2022/9/26 21:35
 */
import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.spi.LoggerContextListener;
import ch.qos.logback.core.Context;
import ch.qos.logback.core.spi.ContextAwareBase;
import ch.qos.logback.core.spi.LifeCycle;
import org.springframework.boot.system.ApplicationHome;

import java.io.File;

public class CustomLogContextListener extends ContextAwareBase implements LoggerContextListener, LifeCycle {

    /** 存储日志路径标识 */
    public static final String LOG_PAHT_KEY = "LOG_HOME";

    @Override
    public void start() {
        //获取当前jar 的执行路径
        ApplicationHome home = new ApplicationHome(getClass());
        File jarFile = null;
        try{
            jarFile = home.getSource() != null ?  home.getSource() : home.getDir();
            String parent = jarFile.getParent();

            String path = jarFile.getParentFile().toString();
            System.out.println("CustomLogContextListener_start_filePath:"+path);
            System.setProperty(LOG_PAHT_KEY, path + File.separator + "logs");
            Context context = getContext();
            context.putProperty(LOG_PAHT_KEY,  path + File.separator + "logs");
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public boolean isResetResistant() {
        return false;
    }

    @Override
    public void onStart(LoggerContext context) {

    }

    @Override
    public void onReset(LoggerContext context) {

    }

    @Override
    public void onStop(LoggerContext context) {

    }

    @Override
    public void onLevelChange(Logger logger, Level level) {

    }

    @Override
    public void stop() {

    }

    @Override
    public boolean isStarted() {
        return false;
    }
}
