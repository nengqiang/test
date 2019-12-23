package com.hnq.study.lock;

import java.util.concurrent.TimeUnit;

/**
 * 两个或两个以上的进程在执行过程中，因争夺资源而造成的一种互相等待的现象，
 * 若无外力干涉他们都将无法进行下去
 *
 * jps -l
 * jstack 进程id
 *
 * @author henengqiang
 * @date 2019/12/11
 */
public class DeadLockDemo {

    public static void main(String[] args) {
        String lockA = "lockA";
        String lockB = "lockB";

        new Thread(new HoldLockThread(lockA, lockB), "AAA").start();
        new Thread(new HoldLockThread(lockB, lockA), "BBB").start();
    }

    static class HoldLockThread implements Runnable {

        private final String lockA;

        private final String lockB;

        public HoldLockThread(String lockA, String lockB) {
            this.lockA = lockA;
            this.lockB = lockB;
        }

        @Override
        public void run() {
            synchronized (lockA) {
                System.out.println(Thread.currentThread().getName() + "自己持有" + lockA + "尝试获得" + lockB);
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lockB) {
                    System.out.println(Thread.currentThread().getName() + "自己持有" + lockB + "尝试获得" + lockA);
                }
            }
        }
    }

}
