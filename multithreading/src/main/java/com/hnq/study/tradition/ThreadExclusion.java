package com.hnq.study.tradition;

import com.hnq.toolkit.util.ThreadPoolUtils;

/**
 * 传统线程互斥技术
 *
 * @author henengqiang
 * @date 2019/05/16
 */
public class ThreadExclusion {

    private static final UnsafeOutputer UNSAFE_OUTPUTER = new UnsafeOutputer();

    private static final SafeOutputer SAFE_OUTPUTER = new SafeOutputer();

    public static void main(String[] args) {
        ThreadPoolUtils.execute(new TaskA(false));
        ThreadPoolUtils.execute(new TaskB(false));
    }

    static class UnsafeOutputer {

        void output(String str) {
            for (int i = 0; i < str.length(); i++) {
                System.out.print(str.charAt(i));
            }
            System.out.println();
        }

    }

    static class SafeOutputer {

        synchronized void output(String str) {
            for (int i = 0; i < str.length(); i++) {
                System.out.print(str.charAt(i));
            }
            System.out.println();
        }

    }

    static class TaskA implements Runnable {
        boolean userSafeThread;

        public TaskA(boolean userSafeThread) {
            this.userSafeThread = userSafeThread;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                String a = "abcdefghijklmn";
                if (userSafeThread) {
                    SAFE_OUTPUTER.output(a);
                } else {
                    UNSAFE_OUTPUTER.output(a);
                }
            }
        }
    }

    static class TaskB implements Runnable {
        boolean userSafeThread;

        public TaskB(boolean userSafeThread) {
            this.userSafeThread = userSafeThread;
        }
        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                String a = "ABCDEFGHIJKLMN";
                if (userSafeThread) {
                    SAFE_OUTPUTER.output(a);
                } else {
                    UNSAFE_OUTPUTER.output(a);
                }
            }
        }
    }

}
