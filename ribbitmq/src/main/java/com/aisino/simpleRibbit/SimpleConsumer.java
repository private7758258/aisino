package com.aisino.simpleRibbit;


import com.rabbitmq.client.Channel;
import com.aisino.model.ChannelUtil;

import java.io.IOException;
public class SimpleConsumer {
    public static void main(String[] args) throws IOException {
        //建立连接
        //建立信道
        Channel channel = ChannelUtil.channel();

        //创建交换机

        //创建消息队列
        //绑定交换机
        //接受消息
        channel.basicConsume("queue52",
                (consumerTag,delivery)->{
                    //处理消息
                    byte[] body = delivery.getBody();
                    String message = new String(body,"utf-8");
                    System.out.println("[x] 收到消息"+message);
                },(consumerTag)->{});
    }
}
