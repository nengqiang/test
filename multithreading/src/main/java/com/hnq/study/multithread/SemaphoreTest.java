package com.hnq.study.multithread;

import com.hnq.toolkit.util.ThreadPoolUtils;

import java.util.Random;
import java.util.concurrent.Semaphore;

/**
 * semaphore(信号灯)可以维护当前访问自身的线程个数，并提供了同步机制。使用Semaphore可以
 * 控制同时访问资源的线程个数，例如实现一个文件允许的并发访问数。
 *
 * Semaphore实现的功能就类似于餐厅有5个座位，假如有10人要就餐，但同时只能
 * 有5个人能够坐下，当5个人的任何一个人让开后，其中在等待的另外5个人中又有
 * 一个人可以坐下了。
 *
 * @author henengqiang
 * @date 2019/05/30
 */
public class SemaphoreTest {

    private static final Semaphore SP = new Semaphore(3);

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            ThreadPoolUtils.execute(new Task());
        }
        ThreadPoolUtils.getThreadPool().shutdown();
    }

    private static class Task implements Runnable {
        @Override
        public void run() {
            try {
                SP.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("线程" + Thread.currentThread().getName() + "进入");

            try {
                Thread.sleep(new Random().nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("线程" + Thread.currentThread().getName() + "即将离开");
            SP.release();
            System.out.println("线程" + Thread.currentThread().getName() + "已离开");
        }
    }

}
