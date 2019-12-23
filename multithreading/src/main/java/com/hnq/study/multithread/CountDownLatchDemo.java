package com.hnq.study.multithread;

import java.util.concurrent.CountDownLatch;
import java.util.stream.IntStream;

/**
 * @author henengqiang
 * @date 2019/11/26
 */
public class CountDownLatchDemo {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(6);
        IntStream.rangeClosed(1, 6).forEach(i -> new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "上完自习，离开教室");
            latch.countDown();
        }, String.valueOf(i)).start());
        latch.await();
        System.out.println(Thread.currentThread().getName() + "班长关教室门走人");
    }

}
