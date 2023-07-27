package com.sbvadmin.quartz;

import com.sbvadmin.common.utils.CommonWebUtil;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;

/**
 * Notes:
 * Author: 涛声依旧 likeboat@163.com
 * Time: 2022/8/8 22:15
 */
@Component
@Slf4j
public class DbBackupJob extends QuartzJobBean {
    private String name;
    public void setName(String name){
        this.name=name;
    }


    @Value("${spring.datasource.username}")
    private String dbUserName;

    @Value("${spring.datasource.password}")
    private String dbpassword;

    @Value("${spring.datasource.database}")
    private String database;

    @Value("${spring.datasource.host}")
    private String host;

    @Value("${spring.datasource.port}")
    private String port;

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss");
        String now = df.format(System.currentTimeMillis());
        System.out.println(name + "定时任务开始执行：" + now);
//        String userDir = System.getProperty("user.dir");
        // 判断要backup文件是否存在
        String backup = CommonWebUtil.getJarPath(DbBackupJob.class) + File.separator + "backup";
        File file = new File(backup);
        if (!file.exists()) {
            file.mkdirs();
        }

        String hostInfo = ""; // 判断是否远程备份数据库
        if (!host.equals("localhost")) hostInfo = " -h " + host + " -P " + port;
        String cmd = "mysqldump"+ hostInfo +" -u" + dbUserName + " -p" + dbpassword +
                " --ignore-table "+ database +".sys_log" + // 移除部分表格，比如那些数据量很大又不重要的表格
                " "+ database +" -r " + backup + File.separator + now + ".sql";
        try {
            log.info("执行语句："+cmd);
            Process exec = Runtime.getRuntime().exec(cmd);
            if (exec.waitFor() == 0) {
                log.info("数据库备份成功，保存路径：" + backup + File.separator + now + ".sql");
            } else {
                System.out.println("process.waitFor()=" + exec.waitFor());
            }
        } catch (IOException e) {
            log.error("备份 数据库 出现 IO异常 ", e);
        } catch (InterruptedException e) {
            log.error("备份 数据库 出现 线程中断异常 ", e);
        } catch (Exception e) {
            log.error("备份 数据库 出现 其他异常 ", e);
        }
    }
}
