package com.hnq.practice.commandpattern;

/**
 * @author henengqiang
 * @date 2019/02/27
 */
public class Retry {

    /**
     * 最多执行task任务maxTimes次
     *
     * @param maxTimes
     * @param task
     * @return
     */
    public static boolean execute(int maxTimes, RetryRunnable task) {
        for (int i = 0; i < maxTimes; i++) {
            try {
                // 什么时候做
                task.run();
                // 执行到这里说明执行没问题
                return true;
            } catch (Exception e) {
                System.err.println("执行出现异常" + e);
            }
        }
        // 执行到这里说明执行出错
        return false;
    }

}
