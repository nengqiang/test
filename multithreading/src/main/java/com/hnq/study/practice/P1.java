package com.hnq.study.practice;

import com.hnq.toolkit.util.ThreadPoolUtils;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 设计4个线程，其中两线程每次对j增加1，另外两线程对j每次减少1
 *
 * @author henengqiang
 * @date 2019/07/04
 */
public class P1 {

    private static AtomicInteger atomicInteger;

    public static void main(String[] args) {
        logic();
    }

    private static void logic() {
        atomicInteger = new AtomicInteger(0);
        for (int i = 0; i < 2; i++) {
            ThreadPoolUtils.execute(new TaskInc());
            ThreadPoolUtils.execute(new TaskDec());
        }
        ThreadPoolUtils.shutdown();
    }

    private static class TaskInc implements Runnable {
        @Override
        public void run() {
            int num = atomicInteger.incrementAndGet();
            System.out.println("Integer after added: " + num);
        }
    }

    private static class TaskDec implements Runnable {
        @Override
        public void run() {
            int num = atomicInteger.decrementAndGet();
            System.out.println("Integer after subtraction: " + num);
        }
    }

}
