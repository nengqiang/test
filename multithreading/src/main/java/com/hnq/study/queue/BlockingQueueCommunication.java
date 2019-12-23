package com.hnq.study.queue;

import com.google.common.collect.Queues;

import java.util.concurrent.BlockingQueue;

/**
 * 用具有1个空间的队列来实现同步通知的功能。
 *
 * 实际上就是两个线程相互通知，线程1做完了通知线程2，
 * 然后线程2做完了通知线程1，如此往来回复。
 *
 * 用具有1个空间的队列就可以实现同步通知，
 * 即线程1与线程2通过阻塞队列的“拿”和“放”
 * 的阻塞规律，实现线程1和线程2交替运行的效果
 *
 * @author henengqiang
 * @date 2019/05/31
 */
public class BlockingQueueCommunication {

    private static BlockingQueue<Integer> queue1 = Queues.newArrayBlockingQueue(1);

    private static BlockingQueue<Integer> queue2 = Queues.newArrayBlockingQueue(1);

    public static void main(String[] args) {
        // 这里只开了两个线程
        try {
            queue1.put(1);
            new Thread(() -> {
                while (true) {
                    executeA();
                }
            }).start();
            while (true) {
                executeB();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void executeA() {
        try {
            queue1.put(1);
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName() + "运行");
            queue2.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void executeB() {
        try {
            queue2.put(1);
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName() + "运行");
            queue1.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
