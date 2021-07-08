package com.aisino.config;

import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.RabbitListenerContainerFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * @author: 86177
 * @create: 2021-07-07 14:37
 * @MONTH_NAME_SHORT: 七月
 * @DAY_NAME_FULL: 星期三
 * @desc
 **/
@Configuration
public class MqConfig {

    @Resource
    private CachingConnectionFactory cachingConnectionFactory;

    //消息监听器的工厂
    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory() {
        SimpleRabbitListenerContainerFactory containerFactory = new SimpleRabbitListenerContainerFactory();
        //连接工厂
        containerFactory.setConnectionFactory(cachingConnectionFactory);
        //ACK模式
        containerFactory.setAcknowledgeMode(AcknowledgeMode.AUTO);
        //欲取数量
        containerFactory.setPrefetchCount(1);
        //并发的消费者数量
        containerFactory.setConcurrentConsumers(1);
        //最大的并发的消费者数量
        containerFactory.setMaxConcurrentConsumers(1);
        //设置反序列化
        containerFactory.setMessageConverter(new Jackson2JsonMessageConverter());
        return containerFactory;
    }



    @Bean
    public RabbitListenerContainerFactory<?> rabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(new Jackson2JsonMessageConverter());
        return factory;
    }
}
