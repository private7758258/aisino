package com.aisino.subScripRibbit;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.aisino.model.ChannelUtil;
import com.aisino.model.Constants;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;

public class SubPublisher {
    public static void main(String[] args) throws IOException, TimeoutException {
        //建立信道和连接
        Channel channel = ChannelUtil.channel();
        //创建交换机,类型是FANOUT
        channel.exchangeDeclare(Constants.Sub_RIBBIT, BuiltinExchangeType.FANOUT);

        //创建队列到消费者执行
        //发送消息
        Scanner scanner = new Scanner(System.in);
        while (true){
            print("请输入你要发送的消息");
            String msg=scanner.next();
            if ("q".equals(msg)){
                break;
            }
            //发送给交换机，不提供routing-key
            channel.basicPublish(Constants.Sub_RIBBIT,"",null,msg.getBytes("utf-8"));
            print("消息发送成功："+msg);
        }
    }
    private static void print(String str) {
        System.out.println("发布者："+str);
    }
}
