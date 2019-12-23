package com.hnq.study.multithread;

import com.hnq.toolkit.util.ThreadPoolUtils;

/**
 * java5条件阻塞Condition的应用
 *
 * 子线程循环10次，接着主线程循环100次，接着又回到子线程循环10次，
 * 接着再回到主线程又循环100次，如此循环50次。
 *
 * @author henengqiang
 * @date 2019/05/23
 */
public class ConditionTest {

    private final Business business = new Business();

    public static void main(String[] args) {
        new ConditionTest().init();
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
        boolean shouldSub = true;

        synchronized void mainThread(int i) {
            if (shouldSub) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            for (int j = 0; j < 100; j++) {
                System.out.println(Thread.currentThread().getName() + ": i=" + i + ", j=" + j);
            }
            shouldSub = true;
            this.notify();
        }

        synchronized void subThread(int i) {
            if (!shouldSub) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            for (int j = 0; j < 10; j++) {
                System.out.println(Thread.currentThread().getName() + ": i=" + i + ", j=" + j);
            }
            shouldSub = false;
            this.notify();
        }
    }

}
