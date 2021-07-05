package com.aisino.routeRibbit;

import com.rabbitmq.client.Channel;
import com.aisino.model.ChannelUtil;
import com.aisino.model.Constants;

import java.io.IOException;

public class RouteConsumer {
    private String name;
    //加入bindingkey,用于队列与交换机的绑定
    private String bindingKey;

    public RouteConsumer(String name, String bindingKey) {
        this.name = name;
        this.bindingKey = bindingKey;
    }

    public void consume() throws IOException{
        //建立信道
        Channel channel = ChannelUtil.channel();
        //创建消息队列
        String queueName = channel.queueDeclare().getQueue();
        //通过bindingKey来绑定交换机
        channel.queueBind(queueName, Constants.ROUTER_RIBBIT,bindingKey);
        //接收消息
        channel.basicConsume(queueName,true,
                (consumerTag,delivery)->{
                    //处理消息
                    byte[] body = delivery.getBody();
                    String message = new String(body,"utf-8");
                    System.out.println(name + "[x] 收到消息"+message);
                },(consumerTag)->{});


    }
}
