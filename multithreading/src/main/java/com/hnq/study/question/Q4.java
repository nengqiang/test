package com.hnq.study.question;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

/**
 * 两个线程对初始值为0的变量进行交替操作，一个加一一个减一
 *
 * @author henengqiang
 * @date 2019/11/27
 */
public class Q4 {

    public static void main(String[] args) {
        traditionalAnswer();
    }

    private static void traditionalAnswer() {
        ShareData shareData = new ShareData();
        IntStream.rangeClosed(1, 5).forEach(i -> new Thread(shareData::increment, "AA").start());

        IntStream.rangeClosed(1, 5).forEach(i -> new Thread(shareData::decrement, "BB").start());

        IntStream.rangeClosed(1, 5).forEach(i -> new Thread(shareData::increment, "CC").start());

        IntStream.rangeClosed(1, 5).forEach(i -> new Thread(shareData::decrement, "DD").start());
    }

    private static class ShareData {
        private int number = 0;
        private Lock lock = new ReentrantLock();
        private Condition condition = lock.newCondition();

        void increment() {
            lock.lock();
            try {
                // 多线程的判断不能用if要用while
                while (number != 0) {
                    condition.await();
                }
                number++;
                System.out.println(Thread.currentThread().getName() + " " + number);
                condition.signalAll();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

        void decrement() {
            lock.lock();
            try {
                while (number == 0) {
                    condition.await();
                }
                number--;
                System.out.println(Thread.currentThread().getName() + " " + number);
                condition.signalAll();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

}
