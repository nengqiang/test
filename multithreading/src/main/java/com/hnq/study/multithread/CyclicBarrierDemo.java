package com.hnq.study.multithread;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.stream.IntStream;

/**
 * @author henengqiang
 * @date 2019/11/26
 */
public class CyclicBarrierDemo {

    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7, () -> System.out.println("召唤神龙"));
        IntStream.rangeClosed(1, 7).forEach(i -> new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "收集到第" + i +"颗龙珠");
            try {
                cyclicBarrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        }, String.valueOf(i)).start());
    }

}
