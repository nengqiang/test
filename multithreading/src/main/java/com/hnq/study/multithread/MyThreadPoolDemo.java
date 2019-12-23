package com.hnq.study.multithread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

/**
 * @author henengqiang
 * @date 2019/12/09
 */
public class MyThreadPoolDemo {

    public static void main(String[] args) {
        System.out.println("核心数=" + getRuntimeProcessors());
//        fixedThreadPool();
//        singleThreadPool();
        cachedThreadPool();
    }

    private static int getRuntimeProcessors() {
        return Runtime.getRuntime().availableProcessors();
    }

    private static void fixedThreadPool() {
        // 执行长期任务 性能好
        ExecutorService threadPool = Executors.newFixedThreadPool(5);
        execute(threadPool, 10);
    }

    private static void singleThreadPool() {
        ExecutorService threadPool = Executors.newSingleThreadExecutor();
        execute(threadPool, 10);
    }

    private static void cachedThreadPool() {
        // 执行短期高并发任务
        ExecutorService threadPool = Executors.newCachedThreadPool();
        execute(threadPool, 200);
    }

    private static void execute(ExecutorService threadPool, int size) {
        try {
            IntStream.rangeClosed(1, size).forEach(i -> threadPool.execute(() ->
                    System.out.println(Thread.currentThread().getName() + "办理业务")));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }
    }

}
