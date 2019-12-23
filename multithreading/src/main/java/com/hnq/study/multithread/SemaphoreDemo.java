package com.hnq.study.multithread;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @author henengqiang
 * @date 2019/11/26
 */
public class SemaphoreDemo {

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);
        IntStream.rangeClosed(1, 6).forEach(i -> new Thread(() -> {
            try {
                semaphore.acquire();
                System.out.println(Thread.currentThread().getName() + " accepted car park");
                TimeUnit.SECONDS.sleep(3);
                System.out.println(Thread.currentThread().getName() + " timeout to leave");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                semaphore.release();
            }
        }, String.valueOf(i)).start());
    }

}
