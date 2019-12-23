package com.hnq.study.multithread;

import com.google.common.collect.Maps;
import com.hnq.toolkit.util.ThreadPoolUtils;

import java.util.Map;
import java.util.Random;

/**
 * 线程范围内共享变量的概念与作用
 *
 * @author henengqiang
 * @date 2019/05/17
 */
public class ThreadShare {

    private static Random r = new Random();

    private static Map<Thread, Integer> threadData = Maps.newHashMap();

    public static void main(String[] args) {
        for (int i = 0; i < 2; i++) {
            ThreadPoolUtils.execute(new Runnable() {
                @Override
                public void run() {
                    putData();
                    getData("A");
                    getData("B");
                }
            });
        }
        ThreadPoolUtils.getThreadPool().shutdown();
    }

    private static void putData() {
        int data = r.nextInt();
        System.out.println(Thread.currentThread().getName() + "放入数据:" + data);
        threadData.put(Thread.currentThread(), data);
    }

    private static void getData(String name) {
        System.out.println(name + "从" + Thread.currentThread().getName() + "中取的数据:" + threadData.get(Thread.currentThread()));
    }

}
