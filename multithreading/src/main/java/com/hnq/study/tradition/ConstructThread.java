package com.hnq.study.tradition;

/**
 * 创建线程的两种方式
 *
 * @author henengqiang
 * @date 2019/05/16
 */
public class ConstructThread {

    public static void main(String[] args) {
        new TaskA().start();
        new Thread(new TaskB()).start();
    }

    static class TaskA extends Thread {
        @Override
        public void run() {
            System.out.println(this.getClass().getName() + " running...");
            super.run();
        }
    }

    static class TaskB implements Runnable {
        @Override
        public void run() {
            System.out.println(this.getClass().getName() + " running...");
        }

    }

}
