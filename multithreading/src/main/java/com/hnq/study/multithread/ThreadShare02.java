package com.hnq.study.multithread;

import com.hnq.toolkit.util.ThreadPoolUtils;

import java.util.Random;

/**
 * ThreadLocal类及应用技巧
 *
 * @author henengqiang
 * @date 2019/05/17
 */
public class ThreadShare02 {

    private static Random r = new Random();

    private static ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>();

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
        threadLocal.remove();
        ThreadPoolUtils.getThreadPool().shutdown();
    }

    private static void putData() {
        int data = r.nextInt();
        System.out.println(Thread.currentThread().getName() + "放入数据:" + data);
        threadLocal.set(data);
    }

    private static void getData(String name) {
        System.out.println(name + "从" + Thread.currentThread().getName() + "中取的数据:" + threadLocal.get());
    }

}
