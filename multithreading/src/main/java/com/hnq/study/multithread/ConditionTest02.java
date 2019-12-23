package com.hnq.study.multithread;

import com.hnq.toolkit.util.ThreadPoolUtils;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * java5条件阻塞Condition的应用
 *
 * 子线程循环10次，接着主线程循环100次，接着又回到子线程循环10次，
 * 接着再回到主线程又循环100次，如此循环50次。
 *
 * @author henengqiang
 * @date 2019/05/23
 */
public class ConditionTest02 {

    private final Business business = new Business();

    public static void main(String[] args) {
        new ConditionTest02().init();
    }

    private void init() {
        ThreadPoolUtils.execute(new Task());

        for (int i = 0; i < 50; i++) {
            business.mainThread(i);
        }

        ThreadPoolUtils.getThreadPool().shutdown();
    }

    private class Task implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 50; i++) {
                business.subThread(i);
            }
        }
    }

    private class Business {
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();

        boolean shouldSub = true;

        void mainThread(int i) {
            lock.lock();
            try {
                while (shouldSub) {
                    try {
                        // 条件阻塞
                        condition.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                for (int j = 0; j < 100; j++) {
                    System.out.println(Thread.currentThread().getName() + ": i=" + i + ", j=" + j);
                }
                shouldSub = true;
                // 同志其他某个线程
                condition.signal();
            } finally {
                lock.unlock();
            }
        }

        void subThread(int i) {
            lock.lock();
            try {
                while (!shouldSub) {
                    try {
                        condition.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                for (int j = 0; j < 10; j++) {
                    System.out.println(Thread.currentThread().getName() + ": i=" + i + ", j=" + j);
                }
                shouldSub = false;
                condition.signal();
            } finally {
                lock.unlock();
            }
        }

    }

}
