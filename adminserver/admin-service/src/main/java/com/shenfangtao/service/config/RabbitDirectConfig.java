package com.shenfangtao.service.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Notes:
 * Author: 涛声依旧 likeboat@163.com
 * Time: 2022/7/27 21:13
 */
@Configuration
public class RabbitDirectConfig {
    @Bean
    Queue queue(){
        return new Queue("add-user");
    }
}
