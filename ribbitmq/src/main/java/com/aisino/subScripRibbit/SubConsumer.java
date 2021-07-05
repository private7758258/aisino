package com.aisino.subScripRibbit;

import com.rabbitmq.client.Channel;
import com.aisino.model.ChannelUtil;
import com.aisino.model.Constants;

import java.io.IOException;

public class SubConsumer {
    private String name;

    public SubConsumer(String name) {
        this.name = name;
    }
    public void consume() throws IOException {
        //建立信道和连接
        Channel channel = ChannelUtil.channel();
        //创建交换机
        //创建消息队列
        String ribbitName=channel.queueDeclare().getQueue();
        //绑定交换机
        channel.queueBind(ribbitName, Constants.Sub_RIBBIT,"");
        //接收消息
        channel.basicConsume(ribbitName,true,
                (consumerTag,delivery)->{
                    //处理消息
                    byte[] body = delivery.getBody();
                    String message = new String(body,"utf-8");
                    System.out.println(name + "[x] 收到消息"+message);
                },(consumerTag)->{});
    }
}
