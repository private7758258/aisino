package com.aisino.routeRibbit;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.aisino.model.ChannelUtil;
import com.aisino.model.Constants;

import java.io.IOException;
import java.util.Scanner;

public class RoutePublisher {
    public static void main(String[] args)throws IOException,RuntimeException {
        //创建信道和连接
        Channel channel = ChannelUtil.channel();
        //创建交换机，类型是DIRECT
        channel.exchangeDeclare(Constants.ROUTER_RIBBIT, BuiltinExchangeType.DIRECT);
        //发送消息
        Scanner scanner = new Scanner(System.in);
        while(true) {
            print("请输入您要发送的消息");
            String message = scanner.next();

            print("请输入您的routing-key");
            String routingKey = scanner.next();
            if("exit".equals(message)){
                break;
            }
            //(2) 发给交换机，提供routing-key
            channel.basicPublish(Constants.ROUTER_RIBBIT, routingKey, null, message.getBytes("UTF-8"));
            print("[x] 消息发送成功！ " + message);
        }
    }
    private static void print(String str) {
        System.out.println("发布者："+str);
    }
}
