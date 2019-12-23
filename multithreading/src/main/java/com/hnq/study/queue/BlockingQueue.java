package com.hnq.study.queue;

import com.hnq.toolkit.util.ThreadPoolUtils;
import lombok.Setter;

import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 阻塞队列
 *
 * @author henengqiang
 * @date 2019/05/31
 */
public class BlockingQueue {

    /*--- 设计一个阻塞队列 ---*/

    private static Lock lock = new ReentrantLock();

    private static Condition notFull = lock.newCondition();

    private static Condition notEmpty = lock.newCondition();

    private static Object[] items = new Object[3];

    private static int puter, taker, count;

    private static Random r = new Random();

    public static void main(String[] args) {
        while (true) {
            for (int i = 0; i < 2; i++) {
                ThreadPoolUtils.execute(new TaskA());
            }
            ThreadPoolUtils.execute(new TaskB());
        }
    }

    private static void put(Object x) throws InterruptedException {
        lock.lock();
        try {
            while (count == items.length) {
                notFull.await();
            }
            items[puter] = x;
            if (++puter == items.length) {
                puter = 0;
            }
            ++count;
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    private static Object take() throws InterruptedException {
        lock.lock();
        try {
            while (count == 0) {
                notEmpty.await();
            }
            Object data = items[taker];
            if (++taker == items.length) {
                taker = 0;
            }
            --count;
            notFull.signal();
            return data;
        } finally {
            lock.unlock();
        }
    }

    private static class TaskA implements Runnable {
        @Setter
        private Integer x = 0;
        @Override
        public void run() {
            try {
                System.out.println(Thread.currentThread().getName() + " 准备put...");
                Thread.sleep(r.nextInt(3000));
                put(x);
                System.out.println(Thread.currentThread().getName() + " put " + x);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static class TaskB implements Runnable {
        @Override
        public void run() {
            try {
                System.out.println(Thread.currentThread().getName() + " 准备take...");
                Thread.sleep(r.nextInt(3000));
                System.out.println(Thread.currentThread().getName() + " take " + take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
