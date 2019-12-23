package com.hnq.study.queue;

import com.google.common.collect.Queues;
import com.hnq.toolkit.util.ThreadPoolUtils;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

/**
 * @author henengqiang
 * @date 2019/05/31
 */
public class BlockingQueue02 {

    private static BlockingQueue<Integer> queue = Queues.newArrayBlockingQueue(3);

    private static Random r = new Random();

    public static void main(String[] args) {
        while (true) {
            for (int i = 0; i < 2; i++) {
                ThreadPoolUtils.execute(new TaskA());
            }
            ThreadPoolUtils.execute(new TaskB());
        }
    }

    private static class TaskA implements Runnable {
        @Override
        public void run() {
            try {
                Thread.sleep(r.nextInt(5000));
                System.out.println(Thread.currentThread().getName() + "准备放数据...");
                queue.put(1);
                System.out.println(Thread.currentThread().getName() + "已经放入了数据，队列目前有" + queue.size() + "个数据");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static class TaskB implements Runnable {
        @Override
        public void run() {
            try {
                Thread.sleep(r.nextInt(5000));
                System.out.println(Thread.currentThread().getName() + "准备取数据...");
                queue.take();
                System.out.println(Thread.currentThread().getName() + "已经取走数据，队列目前有" + queue.size() + "个数据");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
