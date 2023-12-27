package com.sbvadmin.config;

import com.sbvadmin.utils.HandlerRequestJsonArgumentResolver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;
import java.util.List;

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
        if (jarF != null){
            String dirPath = jarF.getParentFile().toString() + File.separator + uploadsPath + File.separator;

            registry.addResourceHandler(File.separator + uploadsPath + File.separator + "**")
                    .addResourceLocations("file:///" + dirPath,
                            "file:///" + dirPath); //  file后面的“///” 是了解决后面路径不生效问题
        }
    }

    /**
     * 注册自定义参数解析器
     */
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new HandlerRequestJsonArgumentResolver());
    }
}
