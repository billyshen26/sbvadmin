package com.sbvadmin.config;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Notes: https://blog.csdn.net/m0_64694079/article/details/134935219  java序列化漏洞问题
 * Author: 涛声依旧 likeboat@163.com
 * Time: 2024/5/30 11:32
 */
@Configuration
public class RabbitMQConfig {

    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
