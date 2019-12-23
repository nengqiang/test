package com.hnq.study.javafunctionprogram;

import java.util.function.Consumer;

/**
 * @author henengqiang
 * @date 2018/10/10
 */
@SuppressWarnings("unchecked")
public class ConsumerTest {

    public static void main(String[] args) {
        Consumer consumer = System.out::println;
        Consumer consumer1 = n -> System.out.println(n + "-F2");

        // 执行完consumer再执行consumer1的Accept方法∞
        consumer.andThen(consumer1).accept("test");

        // 继续执行consumer的Accept方法
        consumer.andThen(consumer).andThen(consumer).accept("test1");
    }


}
