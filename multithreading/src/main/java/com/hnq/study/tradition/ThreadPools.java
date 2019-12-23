package com.hnq.study.tradition;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * java5线程并发库之线程池的应用
 *
 * @author henengqiang
 * @date 2019/05/20
 */
public class ThreadPools {

    public static void main(String[] args) {
//        cachedThread();
//        scheduledThread1();
        scheduledThread2();
    }

    /**
     * 和一般的单独线程不同的是，如果该线程池的线程死掉了，单一线程池会重新创建一个替补线程来保证线程池中始终有一个线程。
     * 也就是说，单一线程池的好处就是，可以实现线程死掉之后重新启动的需求。
     */
    private static void singleThread() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        SingleThreadClazz singleThreadClazz = new SingleThreadClazz();
        executorService.execute(singleThreadClazz);
        // TODO: 2019-05-20 henengqiang: 如何实现自动重启？
    }

    private static class SingleThreadClazz implements Runnable {
        @Override
        public void run() {
            int i = 0;
            while (true) {
                System.out.println(i++);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (i == 5) {
                    Thread.currentThread().stop();
                }
            }
        }
    }

    /**
     * 开启的三个线程都已经同时执行了。但是我们能够看到，直到前3个线程的某一个执行完，
     * 才开始执行第四个线程，所以我们的线程池的大小的确为3。
     */
    private static void fixedThread() {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 10; i++) {
            Task task = new Task();
            task.setI(i);
            executorService.execute(task);
        }
        executorService.shutdown();
    }

    private static void cachedThread() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            Task task = new Task();
            task.setI(i);
            executorService.execute(task);
        }
        executorService.shutdown();
    }

    private static void scheduledThread1() {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(3);
        executorService.schedule(new Task(), 3, TimeUnit.SECONDS);
        executorService.shutdown();
    }

    private static void scheduledThread2() {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(3);
        executorService.scheduleAtFixedRate(new Task(), 3, 1, TimeUnit.SECONDS);
    }

    private static class Task implements Runnable {
        private int i;

        void setI(int i) {
            this.i = i;
        }

        @Override
        public void run() {
            for (int j = 1; j <= 10; j++) {
                System.out.println(Thread.currentThread().getName() + "为第" + i + "个线程，正在执行第" + j + "次循环.");
            }
        }
    }

}
