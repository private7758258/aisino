package com.aisino.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * 死信队列
 */
@Configuration
public class DlxQueueRabbitConfig {

    //正常队列名称
    public static final String NORMAL_DLX_QUEUE = "normal_dlx_queue";
    //正常交换机名称
    public static final String NORMAL_DLX_Exchange = "normal_dlx_exchange";

    //ttl过期时间毫秒
    private static final int NORMAL_DLX_EXPIRATION = 10000;

    //设置正常队列长度限制
    private static final int NORMAL_DLX_LENGTH = 10;

    //死信队列名称
    public static final String DLX_QUEUE = "dlx_queue";
    //死信交换机名称
    public static final String DLX_Exchange = "dlx_exchange";


    //声明正常交换机
    @Bean("normalDlxExchange")
    public TopicExchange normalDlxExchange(){
        return new TopicExchange(NORMAL_DLX_Exchange);
    }

    //声明正常队列绑定死信队列的交换机
    @Bean("normalDlxQueue")
    public Queue normalDlxQueue(){
        return QueueBuilder.durable(NORMAL_DLX_QUEUE)
                .withArgument("x-dead-letter-exchange", DLX_Exchange)
                .withArgument("x-dead-letter-routing-key", "dlx.wq")
                .withArgument("x-message-ttl", NORMAL_DLX_EXPIRATION)
                .withArgument("x-max-length",NORMAL_DLX_LENGTH)
                .build();
    }

    //声明正常队列和正常交换机的绑定
    @Bean
    public Binding normalDlxBinding(){
        return BindingBuilder.bind(normalDlxQueue()).to(normalDlxExchange()).with("test.dlx.#");
    }

//=========================================================================

    //声明死信队列
    @Bean
    public Queue dlxQueue(){
        return new Queue(DLX_QUEUE);
    }
    //声明死信交换机
    @Bean
    public TopicExchange dlxExchange(){
        return new TopicExchange(DLX_Exchange);
    }
    //声明死信队列和死信交换机的绑定
    @Bean
    public Binding dlxBinding(){
        return BindingBuilder.bind(dlxQueue()).to(dlxExchange()).with("dlx.#");
    }


}