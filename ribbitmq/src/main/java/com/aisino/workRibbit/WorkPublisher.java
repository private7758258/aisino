package com.aisino.workRibbit;

import com.rabbitmq.client.Channel;
import com.aisino.model.ChannelUtil;
import com.aisino.model.Constants;

import java.util.Scanner;
import java.util.concurrent.TimeoutException;
import java.io.IOException;

public class WorkPublisher {
    public static void main(String[] args)throws IOException, TimeoutException  {
        //建立连接和通道
        Channel channel = ChannelUtil.channel();
        //创建交换机，类型是FANPUT
        channel.queueDeclare(Constants.Sub_RIBBIT, false,false,false,null);
        //创建队列到消费者端执行
        Scanner scanner = new Scanner(System.in);
        while (true){
            print("请输入你要发送的消息");
            String msg=scanner.next();
            if ("q".equals(msg)){
                break;
            }
            //发送给交换机    不提供routing-key
            channel.basicPublish("",Constants.Sub_RIBBIT,null,msg.getBytes("utf-8"));
            print("消息发送成功:  "+msg);

        }
    }
    private static void print(String str) {
        System.out.println("发布者："+str);
    }
}
