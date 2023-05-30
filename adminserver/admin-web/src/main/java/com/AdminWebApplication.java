package com;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
//@EnableCaching 这个配置放到了 com.sbvadmin.service.config.CustomRedisCacheManager
@MapperScan("com.sbvadmin.mapper")
public class AdminWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdminWebApplication.class, args);
    }

    /**
     * Notes:  解决Invalid haracter found in the request target 异常
     * https://blog.csdn.net/tianzhonghaoqing/article/details/125806385
     * @param: []
     * @return: org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory
     * Author: 涛声依旧 likeboat@163.com
     * Time: 2022/11/18 15:28
     **/
    @Bean
    public ConfigurableServletWebServerFactory webServerFactory() {
        TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory();
        factory.addConnectorCustomizers((TomcatConnectorCustomizer) connector -> connector.setProperty("relaxedQueryChars", "|{}[]\\"));
        return factory;
    }
}
