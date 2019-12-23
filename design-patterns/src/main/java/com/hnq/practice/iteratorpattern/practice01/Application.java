package com.hnq.practice.iteratorpattern.practice01;

import com.hnq.practice.iteratorpattern.practice01.client.UserRequestMgt;
import com.hnq.practice.iteratorpattern.practice01.model.UserRequest;

/**
 * 1 Aggregate
 *  Aggregate 是集合接口，声明了迭代器对象创建方法 CreateIterator。在JAVA中，已经定义了 Aggregate 接口，
 *  即 java.lang.Iterable，迭代器对象的创建方法为 iterator。
 * 2 ConcreteAggregate
 *  ConcreteAggregate 是具体集合类，实现了 Aggregate 接口。在JAVA中，即为实现了 java.lang.Iterable 接口的类。
 *  其中，iterator 方法返回的迭代器对象为具体迭代器类对象。
 * 3 Iterator
 *  Iterator 是迭代器接口，声明了迭代器应实现的各方法。在JAVA中，已经定义了 Iterator 接口，即java.util.Iterator。
 * 4 ConcreteIterator
 *  ConcreteIterator 是具体迭代器类，实现了 Iterator 接口。在JAVA中，即为实现了 java.util.Iterator 接口的类。
 *  为了实现对集合的遍历，ConcreteIterator 类需要维护其要遍历的集合的引用。
 *  另外，拥有较好健壮性的迭代器应该可以支持遍历过程中集合元素的增删改等变化。
 * 5 Client
 *  Client 是客户类，是迭代器模式的使用者。Client 构建集合对象，并调用其 CreateIterator 方法创建迭代器对象。再使用迭代器对象遍历集合。
 *
 * 场景介绍：
 *  某银行柜台排队系统根据一定的策略对请求进行排队处理。请求按照VIP用户优先；存款业务优先；先到先处理三个条件排序。
 * 以下各节将介绍该场景各类的具体实现及其在迭代器设计模式中所对应的参与者角色。
 *
 * @author henengqiang
 * @date 2019/05/31
 */
public class Application {

    public static void main(String[] args) {
        try {
            iteratorTest();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void iteratorTest() throws InterruptedException {
        // 用户请求管理
        UserRequestMgt userRequestMgt = new UserRequestMgt();
        // 用户请求1
        UserRequest userRequest1 = new UserRequest("001", false, 1);
        userRequestMgt.putRequest(userRequest1);
        Thread.sleep(1000);

        // 用户请求2
        UserRequest userRequest2 = new UserRequest("002", false, 0);
        userRequestMgt.putRequest(userRequest2);
        Thread.sleep(1000);

        //用户请求3
        UserRequest userRequest3 = new UserRequest("003", false, 0);
        userRequestMgt.putRequest(userRequest3);
        Thread.sleep(1000);

        // 响应一次请求
        System.out.println(userRequestMgt.getRequest());

        // 用户请求4
        UserRequest userRequest4 = new UserRequest("004", true, 2);
        userRequestMgt.putRequest(userRequest4);
        Thread.sleep(1000);

        // 用户请求5
        UserRequest userRequest5 = new UserRequest("005", true, 0);
        userRequestMgt.putRequest(userRequest5);
        Thread.sleep(1000);

        // 响应所有剩余请求
        while (true) {
            // 当前请求
            UserRequest userRequest = userRequestMgt.getRequest();
            if (userRequest == null) {
                break;
            }
            System.out.println(userRequest);
        }
    }

}
