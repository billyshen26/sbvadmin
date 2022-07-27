package com.shenfangtao.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Notes:
 * Author: 涛声依旧 likeboat@163.com
 * Time: 2022/7/27 21:18
 */
@Component
public class TestDirectReceiver {
//    @RabbitListener(queues = "add-user")
//    public void handler1(String msg){
//        System.out.println("有新用户添加进来了:" + msg);
//    }
}
