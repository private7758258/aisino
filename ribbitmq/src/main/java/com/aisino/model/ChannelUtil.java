package com.aisino.model;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class ChannelUtil {
    public static Channel channel(){
        //建立连接
        try {
        ConnectionFactory cf = new ConnectionFactory();
        cf.setHost("localhost");
        cf.setPort(5672);
        cf.setUsername("root");
        cf.setPassword("root");
        cf.setVirtualHost("/");
        Connection connection = null;

        connection = cf.newConnection();
        //建立信道
        Channel channel = connection.createChannel();
        return  channel;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
      return null;
    }
}
