package com.hnq.study.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author henengqiang
 * @date 2019/11/26
 */
public class BlockingQueueProConsumerDemo {

    public static void main(String[] args) {
        MyResource myResource = new MyResource(new ArrayBlockingQueue<>(10));
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " 生产线程启动");
            try {
                TimeUnit.MILLISECONDS.sleep(100);
                myResource.produce();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "pro").start();
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " 消费线程启动");
            try {
                TimeUnit.MILLISECONDS.sleep(100);
                myResource.consume();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "consumer").start();

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("生产消费结束");
        myResource.stop();
    }

    private static class MyResource {
        /**
         * 默认开启，进行生产+消费
         */
        private volatile boolean flag = true;
        private AtomicInteger atomicInteger = new AtomicInteger();

        BlockingQueue<String> blockingQueue = null;

        public MyResource(BlockingQueue<String> blockingQueue) {
            this.blockingQueue = blockingQueue;
            System.out.println(blockingQueue.getClass().getName());
        }

        public void produce() throws InterruptedException {
            String data = "";
            boolean returnVal;
            while (flag) {
                data = String.valueOf(atomicInteger.incrementAndGet());
                returnVal = blockingQueue.offer(data, 2L, TimeUnit.SECONDS);
                if (returnVal) {
                    System.out.println(Thread.currentThread().getName() + " 插入队列 " + data + " 成功");
                } else {
                    System.out.println(Thread.currentThread().getName() + " 插入队列 " + data + " 失败");
                }
                TimeUnit.SECONDS.sleep(1);
            }
            System.out.println(Thread.currentThread().getName() + " 停止生产");
        }

        public void consume() throws InterruptedException {
            String result;
            while (flag) {
                result = blockingQueue.poll(2L, TimeUnit.SECONDS);
                if (result == null || "".equalsIgnoreCase(result)) {
                    flag = false;
                    System.out.println(Thread.currentThread().getName() + " 超过2s没有取到数据，结束消费");
                    return;
                }
                System.out.println(Thread.currentThread().getName() + " 消费队列 " + result + " 成功");
            }
        }

        public void stop() {
            flag = false;
        }
    }

}
