package com.hnq.study.multithread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author henengqiang
 * @date 2019/12/03
 */
public class CallableDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<Integer> futureTask = new FutureTask<>(new MyThread());

        new Thread(futureTask, "AAA").start();

        System.out.println("result=" + futureTask.get());
    }

    private static class MyThread2 implements Runnable {
        @Override
        public void run() {

        }
    }

    private static class MyThread implements Callable<Integer> {
        @Override
        public Integer call() throws Exception {
            System.out.println("come in Callable");
            return 1024;
        }
    }

}
