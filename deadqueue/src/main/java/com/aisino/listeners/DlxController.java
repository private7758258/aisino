package com.aisino.listeners;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class DlxController {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    //http://127.0.0.1:8081/dlx/testTimeDLX
    //测试时间过期
    @GetMapping("testTimeDLX")
    public String testTimeDLX() {
        String messageId = String.valueOf(UUID.randomUUID());
        //normal_dlx_exchange正常交换机  test.dlx.wq:正常交换机与正常绑定的队列的路由
        rabbitTemplate.convertAndSend("normal_dlx_exchange", "test.dlx.wq", messageId+"变成死信队列消息");
        return "ok";
    }


}