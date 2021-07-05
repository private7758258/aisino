package com.aisino.confirmRibbit;

import java.io.IOException;

/**
 * Hello world!
 *
 */
public class ConfimApp {
    public static void main( String[] args )throws IOException{
        //确认模式
        new ConfimConsumer("笑笑","A").consume();

        new ConfimConsumer("零零","B").consume();
        new ConfimConsumer("毛利","C").consume();
    }
}
