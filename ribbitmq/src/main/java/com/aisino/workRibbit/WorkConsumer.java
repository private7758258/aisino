package com.aisino.workRibbit;

import com.rabbitmq.client.Channel;
import com.aisino.model.ChannelUtil;
import com.aisino.model.Constants;

import java.io.IOException;

public class WorkConsumer {
    private String name;

    public WorkConsumer(String name) {
        this.name = name;
    }
    public void consume() throws IOException {
        //建立信道和连接
        Channel channel = ChannelUtil.channel();
        //创建消息队列
        //绑定在交换机上
        //监听队列中的消息接收消息
        channel.basicConsume(
                Constants.Sub_RIBBIT,
                true,
                (consumerTag,delivery)->{
                    //处理消息
                    byte[] body = delivery.getBody();
                    String message = new String(body,"utf-8");
                    System.out.println(name + " 收到消息是："+message);
                },(consumerTag)->{});

    }

}
