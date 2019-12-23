package com.hnq.study.lock;

import com.hnq.toolkit.consts.BannerConsts;
import com.hnq.toolkit.util.ThreadPoolUtils;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author henengqiang
 * @date 2019/05/21
 */
public class ReadWriteLock {

    public static void main(String[] args) {

    }

    private static void noLockTest() {
        TaskA taskA = new TaskA();
        TaskB taskB = new TaskB();
        taskA.setSelectOutput(1);
        taskB.setSelectOutput(1);
        ThreadPoolUtils.execute(taskA);
        ThreadPoolUtils.execute(taskB);
    }

    private static void lockTest() {
        TaskA taskA = new TaskA();
        TaskB taskB = new TaskB();
        taskA.setSelectOutput(2);
        taskB.setSelectOutput(2);
        ThreadPoolUtils.execute(taskA);
        ThreadPoolUtils.execute(taskB);
    }

    private static class TaskA implements Runnable {
        private Integer selectOutput;

        void setSelectOutput(Integer selectOutput) {
            this.selectOutput = selectOutput;
        }

        @Override
        public void run() {
            String str = buildChars('a', 'z');
            while (true) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                switch (selectOutput) {
                    case 1:
                        unsafeOutput(str);
                        break;
                    case 2:
                        safeOutput(str);
                        break;
                    default:
                        break;
                }
            }
        }
    }

    private static class TaskB implements Runnable {
        private Integer selectOutput;

        void setSelectOutput(Integer selectOutput) {
            this.selectOutput = selectOutput;
        }
        @Override
        public void run() {
            String str = buildChars('A', 'Z');
            while (true) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                switch (selectOutput) {
                    case 1:
                        unsafeOutput(str);
                        break;
                    case 2:
                        safeOutput(str);
                        break;
                    default:
                        break;
                }
            }
        }
    }

    private static String buildChars(char start, char end) {
        StringBuilder sb = new StringBuilder();
        do {
            sb.append(start);
            start += 1;
        } while (start <= end);
        return sb.toString();
    }

    private static void unsafeOutput(String str) {
        for (int i = 0; i < str.length(); i++) {
            System.out.print(str.charAt(i));
        }
        System.out.println();
    }

    private static Lock lock = new ReentrantLock();

    private static void safeOutput(String str) {
        // 上锁
        lock.lock();
        try {
            for (int i = 0; i < str.length(); i++) {
                System.out.print(BannerConsts.BLUE_PREFIX + str.charAt(i) + BannerConsts.COLOR_SUFFIX);
            }
            System.out.println();
        } finally {
            // 这么做是防止线程死掉大家都进不去
            lock.unlock();
        }
    }

}
