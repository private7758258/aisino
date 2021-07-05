package com.aisino;

import com.aisino.workRibbit.WorkConsumer;

import java.io.IOException;

/**
 * Hello world!
 *
 */
public class App{
    public static void main( String[] args )throws IOException{
        //工作模式
        new WorkConsumer("华为").consume();
        new WorkConsumer("小米").consume();
        //订阅模式
//        new SubConsumer("小张").consume();
//        new  SubConsumer("小李").consume();
        //路由模式
//        new RouteConsumer("貂蝉","123").consume();
//        new RouteConsumer("吕布","456").consume();
        //主题模式
//        new TopicConsumer("*.orange.*","*.orange.*").consume();
//        new TopicConsumer("*.*.rabbit","*.*.rabbit").consume();
//        new TopicConsumer("lazy.#","lazy.#").consume();
    }
}
