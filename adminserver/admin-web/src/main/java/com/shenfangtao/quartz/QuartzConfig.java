package com.shenfangtao.quartz;

import org.quartz.JobDataMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.*;

/**
 * Notes:
 * Author: 涛声依旧 likeboat@163.com
 * Time: 2022/8/8 22:17
 */
@Configuration
public class QuartzConfig {
   /**
    * Notes:  配置jobDetail，传入部分参数
    * @param: []
    * @return: org.springframework.scheduling.quartz.JobDetailFactoryBean
    * Author: 涛声依旧 likeboat@163.com
    * Time: 2022/8/8 22:23
    **/
    @Bean
    JobDetailFactoryBean dbBackupBean(){
        JobDetailFactoryBean bean = new JobDetailFactoryBean();
        bean.setJobClass(DbBackupJob.class);
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("name","数据库备份");
        bean.setJobDataMap(jobDataMap);
        bean.setDurability(true);
        return bean;
    }

    /**
     * Notes:  配置trigger，设置运行规则
     * @param: []
     * @return: org.springframework.scheduling.quartz.CronTriggerFactoryBean
     * Author: 涛声依旧 likeboat@163.com
     * Time: 2022/8/8 22:30
     **/

    @Bean
    CronTriggerFactoryBean cronTrigger() {
        CronTriggerFactoryBean bean = new CronTriggerFactoryBean();
        bean.setJobDetail(dbBackupBean().getObject());
        bean.setCronExpression("0 0/1 * * * ?"); // 1分钟一次，https://www.pppet.net/
        return bean;
    }

    /**
     * Notes:  
     * @param: [] 工厂加入trigger
     * @return: org.springframework.scheduling.quartz.SchedulerFactoryBean
     * Author: 涛声依旧 likeboat@163.com
     * Time: 2022/8/10 21:51
     **/
    SchedulerFactoryBean schedulerFactory(){
        SchedulerFactoryBean bean = new SchedulerFactoryBean();
        bean.setTriggers(cronTrigger().getObject());
        return bean;
    }
}
