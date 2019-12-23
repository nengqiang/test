package com.hnq.study.practice;

import com.google.common.collect.Lists;
import com.hnq.toolkit.util.ThreadPoolUtils;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

/**
 * @author henengqiang
 * @date 2019/07/10
 */
class AutoDistributionTest {

    private final ExecutorService threadPool = Executors.newFixedThreadPool(10);

    @Test
    void testThread() throws Exception {
        List<Integer> integers = new ArrayList<>();
        IntStream.rangeClosed(1, 50).forEach(integers::add);
        CountDownLatch countDownLatch = new CountDownLatch(integers.size());
        for (Integer integer : integers) {
            threadPool.submit(() -> {
                try {
                    System.out.println(integer);
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    countDownLatch.countDown();
                }
            });
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            System.out.println("等待线程结束出现异常");
        }
        System.out.println("任务结束");
    }

    @Test
    void myTest() {
        List<Integer> integers = Lists.newArrayList();
        IntStream.rangeClosed(1, 50).forEach(integers::add);
        CountDownLatch countDownLatch = new CountDownLatch(integers.size());
        for (Integer integer : integers) {
            ThreadPoolUtils.execute(() -> {
                try {
                    System.out.println(integer);
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    countDownLatch.countDown();
                }
            });
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            System.out.println("等待线程结束出现异常");
        }
        System.out.println("任务结束");
    }

}
