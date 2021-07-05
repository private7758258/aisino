package com.aisino.simpleRibbit;

import com.rabbitmq.client.Channel;
import com.aisino.model.ChannelUtil;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;

public class SimplePublisher {

    public static void main(String[] args) throws IOException, TimeoutException {
        //创建交换机
        //创建消息队列
         /*
                queue – 队列名
                durable – 是否持久化，如果为true则会持久化 (服务器重启后仍存在)
                exclusive – 是否独占，true则为独占队列，其他链接不能使用
                autoDelete – 是否自动删除消息，true则在使用完后删除
                arguments – map类型，其他参数
            * */
        Channel channel = ChannelUtil.channel();
        channel.queueDeclare("queue52",false,false,true,null);

        //发送消息
        /*
         exchange 交换机
         routing-key 路由键
         properties 属性
         body 消息体
         */
        while (true){
            Scanner scanner = new Scanner(System.in);
            print("请输入你要发送的消息");
            String msg =scanner.next();
            channel.basicPublish("","queue52",null,msg.getBytes("UTF-8"));
            System.out.println("[x] 消息发送成功！ "+msg);
            if (msg=="break"){
                break;
            }
        }


    }
    private static void print(String str) {

        System.out.println("发布者："+str);

    }
}
