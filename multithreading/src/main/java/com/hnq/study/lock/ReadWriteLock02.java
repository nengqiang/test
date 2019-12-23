package com.hnq.study.lock;

import com.hnq.toolkit.util.ThreadPoolUtils;

import java.util.Random;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author henengqiang
 * @date 2019/05/21
 * @see <a href="https://blog.csdn.net/acmman/article/details/52902128">original article</a>
 */
public class ReadWriteLock02 {

    // TODO: 2019-05-22 henengqiang 没怎么搞懂

    private static Random r = new Random();

    public static void main(String[] args) {
        readWriteLockTest();
    }

    private static void readWriteLockTest() {
        for (int i = 0; i < 3; i++) {
            Task task1 = new Task();
            Task task2 = new Task();
            task1.setWrite(true);
            task2.setWrite(false);
            ThreadPoolUtils.execute(task1);
            ThreadPoolUtils.execute(task2);
        }
        ThreadPoolUtils.getThreadPool().shutdown();
    }

    private static class Task implements Runnable {
        final Queue q = new Queue();
        private boolean isWrite;

        void setWrite(boolean write) {
            isWrite = write;
        }

        @Override
        public void run() {
            if (isWrite) {
                q.put(r.nextInt(1000));
            } else {
                q.get();
            }
        }
    }

    private static class Queue {
        /**
         * 共享数据
         */
        private Object data;

        private ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();

        void get() {
            rwl.readLock().lock();
            try {
                System.out.println(Thread.currentThread().getName() + "准备读取数据...");
                Thread.sleep(r.nextInt(10));
                System.out.println(Thread.currentThread().getName() + "读取到数据：" + data);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                rwl.readLock().unlock();
            }
        }

        void put(Object data) {
            rwl.writeLock().lock();
            try {
                System.out.println(Thread.currentThread().getName() + "准备改写数据...");
                Thread.sleep(10);
                this.data = data;
                System.out.println(Thread.currentThread().getName() + "改写数据为：" + data);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                rwl.writeLock().unlock();
            }
        }
    }

}
