package com.aisino.topicRibbit;

import com.rabbitmq.client.Channel;
import com.aisino.model.ChannelUtil;
import com.aisino.model.Constants;

import java.io.IOException;

public class TopicConsumer {
    private String name;
    //加入bindingKey,用于队列与交换机的绑定
    private String bindingKey;

    public TopicConsumer(String name, String bindingKey) {
        this.name = name;
        this.bindingKey = bindingKey;
    }
    public void consume() throws IOException{
        //建立信道和连接
        Channel channel = ChannelUtil.channel();

        //创建消息d队列
        String queueName=channel.queueDeclare().getQueue();
        //通过bingingKey来绑定交换机
        channel.queueBind(queueName,Constants.TOPIC_RIBBIT,bindingKey);
        //接收消息
        channel.basicConsume(queueName,true,
                (consumerTag,delivery)->{
                    //处理消息
                    byte[] body = delivery.getBody();
                    String message = new String(body,"utf-8");
                    System.out.println(name + " 收到消息"+message);
                },(consumerTag)->{});
    }
}
