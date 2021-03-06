package com.shenfangtao.mailserver.receiver;

import com.rabbitmq.client.Channel;
import com.shenfangtao.model.User;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;

/**
 * Notes:
 * Author: 涛声依旧 likeboat@163.com
 * Time: 2022/7/28 14:34
 */
@Component
public class MailReceiver {

    @Autowired
    JavaMailSender javaMailSender;

    @Autowired
    TemplateEngine templateEngine;

    @RabbitListener(queues = "add-user")
    public void handler(Message message, Channel channel) throws IOException {
        User user = (User) message.getPayload();
        System.out.println("有新用户添加进来了:" + user.getName());

        Context ctx = new Context();
        ctx.setVariable("username",user.getUsername());
        ctx.setVariable("name",user.getName());
        String mail = templateEngine.process("register.html", ctx);

        sendHtmlMail("493058179@qq.com",
                "billyshen26@gmail.com",
                "shenfangtao@imaodu.com",
                "html邮件",
                mail);

//        sendSimpleMail("493058179@qq.com",
//                "billyshen26@gmail.com",
//                "shenfangtao@imaodu.com",
//                user.getName(),
//                user.getEmail());
    }

    public void sendHtmlMail(String from, String to, String cc, String subject, String content){
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setBcc(cc);
            helper.setSubject(subject);
            helper.setText(content);
            javaMailSender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendSimpleMail(String from, String to, String cc, String subject, String content) {
        SimpleMailMessage simpMsg = new SimpleMailMessage();
        simpMsg.setFrom(from);
        simpMsg.setTo(to);
        simpMsg.setCc(cc);
        simpMsg.setSubject(subject);
        simpMsg.setText(content);
        javaMailSender.send(simpMsg);
    }
}
