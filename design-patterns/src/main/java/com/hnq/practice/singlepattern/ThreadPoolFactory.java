package com.hnq.practice.singlepattern;

import java.util.concurrent.*;

/**
 * 静态内部类单例 - 线程安全 - 工作中常用
 *
 * @author henengqiang
 * @date 2019/02/27
 */
public class ThreadPoolFactory {

    /**
     * 获取定时调度的线程池
     */
    public static ScheduledExecutorService getScheduledPool() {
       return ScheduledThreadPoolHolder.SCHEDULED_POOL;
    }

    /**
     * 获取自定义的线程池
     */
    public static ThreadPoolExecutor getPoolExecutor() {
        return ThreadPoolExecutorHolder.POOL_EXECUTOR;
    }

    public static ExecutorService getExecutorService() {
        return CachedThreadPoolHolder.POOL;
    }

    // ------------------------------------------------

    /**
     * 设置线程池
     */
    public static class CachedThreadPoolHolder {
        private static final ExecutorService POOL = Executors.newCachedThreadPool();
    }

    /**
     * 定时调度线程池
     */
    public static class ScheduledThreadPoolHolder {
        private static final ScheduledExecutorService SCHEDULED_POOL = new ScheduledThreadPoolExecutor(5);
    }

    /**
     * 创建自定义的线程池
     */
    public static class ThreadPoolExecutorHolder {
        private static final ThreadPoolExecutor POOL_EXECUTOR = new ThreadPoolExecutor(
                40,
                80,
                30,
                TimeUnit.MINUTES,
                new LinkedBlockingDeque<Runnable>(),
                new ThreadPoolExecutor.DiscardPolicy());
    }
}
