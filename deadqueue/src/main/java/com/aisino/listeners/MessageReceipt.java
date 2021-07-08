package com.aisino.listeners;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author: 86177
 * @create: 2021-07-07 15:44
 * @MONTH_NAME_SHORT: 七月
 * @DAY_NAME_FULL: 星期三
 * @desc: 生产者
 **/
@Component
@Slf4j
public class MessageReceipt {

    @RabbitListener(queues ="dlx_queue")
    public void receiveA(@Payload Message message, Channel channel,
                         @Header(AmqpHeaders.DELIVERY_TAG) long delibertTag
    ) throws IOException {
        channel.basicAck(delibertTag, false);
        log.info("消费超时的内容：{}", new String(message.getBody()));

    }
}
