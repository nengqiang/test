package com.hnq.study.question;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

/**
 * A -> 5 B -> 10 C -> 15
 * A -> 5 B -> 10 C -> 15
 *
 * 10轮
 *
 * @author henengqiang
 * @date 2019/11/27
 */
public class Q5 {

    public static void main(String[] args) {
        ShareData shareData = new ShareData();
        new Thread(() -> IntStream.rangeClosed(1, 10).forEach(i -> shareData.print5()), "A").start();
        new Thread(() -> IntStream.rangeClosed(1, 10).forEach(i -> shareData.print10()), "B").start();
        new Thread(() -> IntStream.rangeClosed(1, 10).forEach(i -> shareData.print15()), "C").start();
    }

    private static class ShareData {
        /**
         * A=1 B=2 C=3
         */
        private int number = 1;
        private Lock lock = new ReentrantLock();
        private Condition c1 = lock.newCondition();
        private Condition c2 = lock.newCondition();
        private Condition c3 = lock.newCondition();

        void print5() {
            lock.lock();
            try {
                while (number != 1) {
                    c1.await();
                }
                IntStream.rangeClosed(1, 5).forEach(i -> System.out.println(Thread.currentThread().getName() + " " + i));
                number = 2;
                c2.signal();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

        void print10() {
            lock.lock();
            try {
                while (number != 2) {
                    c2.await();
                }
                IntStream.rangeClosed(1, 10).forEach(i -> System.out.println(Thread.currentThread().getName() + " " + i));
                number = 3;
                c3.signal();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

        void print15() {
            lock.lock();
            try {
                while (number != 3) {
                    c3.await();
                }
                IntStream.rangeClosed(1, 15).forEach(i -> System.out.println(Thread.currentThread().getName() + " " + i));
                number = 1;
                c1.signal();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

    @AllArgsConstructor
    @Getter
    private static enum ThreadNameEnum {
        /**
         *
         */
        A("A", 1), B("B", 2), C("C", 3);
        private String name;
        private int code;
        public static String codeToName(int code) {
            for (ThreadNameEnum value : ThreadNameEnum.values()) {
                if (code == value.getCode()) {
                    return value.getName();
                }
            }
            return "";
        }
    }

}
