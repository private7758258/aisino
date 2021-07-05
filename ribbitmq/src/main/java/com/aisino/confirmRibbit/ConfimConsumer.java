package com.aisino.confirmRibbit;

import com.rabbitmq.client.Channel;
import com.aisino.model.ChannelUtil;
import com.aisino.model.Constants;

import java.io.IOException;

public class ConfimConsumer {
    private String name;
    //加入bindingkey,用于队列与交换机的绑定
    private String bindingKey;

    public ConfimConsumer(String name, String bindingKey) {
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
        //第二个参数false为手动确认
        channel.basicConsume(queueName,false,
                (consumerTag,delivery)->{
                    //处理消息
                    byte[] body = delivery.getBody();
                    String message = new String(body,"utf-8");
                    System.out.println(name + "[x] 收到消息"+message);

                    long deliveryTag = delivery.getEnvelope().getDeliveryTag();
                    System.out.println("deliveryTag"+delivery+"consumerTag"+consumerTag);
                    //消息id
                    //是否是批量模式
                    //是否放入队列
                    //消息id
                    //批量模式
                    //是否放入队列
                    if (deliveryTag%2==0){
                        System.out.println("拒收");
                        //偶数拒收
                        channel.basicNack(deliveryTag,false,true);
                    }else{
                        System.out.println("接受");
                        //奇数收
                        channel.basicAck(deliveryTag,false);
                    }
                },(consumerTag)->{});
    }
}
