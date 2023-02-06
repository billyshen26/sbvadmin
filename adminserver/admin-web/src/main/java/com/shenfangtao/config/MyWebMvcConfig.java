package com.shenfangtao.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.io.File;

/**
 * Notes:
 * Author: 涛声依旧 likeboat@163.com
 * Time: 2023/2/6 16:30
 */
@Configuration
public class MyWebMvcConfig implements WebMvcConfigurer {

    @Value("${application.uploadsPath}")
    private String uploadsPath;

    // 配置上传的文件外部访问
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        ApplicationHome h = new ApplicationHome(getClass());
        File jarF = h.getSource();
        String dirPath = jarF.getParentFile().toString() + File.separator + uploadsPath + File.separator;

        registry.addResourceHandler(File.separator + uploadsPath + File.separator + "**")
                .addResourceLocations("file:///" + dirPath,
                        "file:///" + dirPath); //  file后面的“///” 是了解决后面路径不生效问题
    }
}
