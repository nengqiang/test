package com.hnq.study.question;

import com.google.common.collect.Queues;
import com.hnq.toolkit.util.ThreadPoolUtils;
import lombok.Setter;

import java.util.concurrent.Semaphore;
import java.util.concurrent.SynchronousQueue;

/**
 * 现有的Test类中代码在不断的产生数据，然后交给TestDo.doSome()方法去处理，就好像生产者
 * 在不断地产生数据，消费者在不断消费数据。请将程序改造成有10个线程来消费生成者产生的数据，
 * 这些消费者都调用TestDo.doSome()方法去进行处理，故每个消费者都需要一秒才能处理完，程序
 * 应该保证这些消费者线程依次有序地消费数据，只有上一个消费者消费完后，下一个消费者才能
 * 消费数据，下一个消费者是谁都可以，但要保证这些消费者线程拿到的数据是有顺序的。
 *
 * @author henengqiang
 * @date 2019/06/03
 * @see <a href="https://blog.csdn.net/acmman/article/details/53125959">Answer</a>
 */
public class Interview2 {

    public static void main(String[] args) {
        System.out.println("begin:" + (System.currentTimeMillis() / 1000));
        // 这行不能改动
        for (int i = 0; i < 10; i++) {
            // 这行不能改动
            String input = i + "";
            String output = TestDo.doSome(input);
            System.out.println(Thread.currentThread().getName() + ":" + output);
        }
    }
}

/**
 * 不能改动此TestDao类
 */
class TestDo {
    static String doSome(String input) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return input + ":" + (System.currentTimeMillis() / 1000);
    }
}

/**
 * // TODO: 2019-06-03 henengqiang 有问题
 */
class Q2Answer1 {

    private static final Semaphore SP = new Semaphore(1);

    public static void main(String[] args) {
        System.out.println("begin:" + (System.currentTimeMillis() / 1000));
        // 这行不能改动
        for (int i = 0; i < 10; i++) {
            // 这行不能改动
            String input = i + "";
            Task task = new Task();
            task.setInput(input);
            ThreadPoolUtils.execute(task);
        }
        ThreadPoolUtils.shutdown();
    }

    private static class Task implements Runnable {
        @Setter
        private String input;

        @Override
        public void run() {
            try {
                SP.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            String output = TestDo.doSome(input);
            System.out.println(Thread.currentThread().getName() + ":" + output);
            SP.release();
        }
    }
}

/**
 * // TODO: 2019-06-03 henengqiang 不是很理解
 */
class Q2Answer2 {

    public static void main(String[] args) {
        final Semaphore semaphore = new Semaphore(1);
        final SynchronousQueue<String> queue = Queues.newSynchronousQueue();
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    String input = null;
                    try {
                        semaphore.acquire();
                        input = queue.take();
                        String output = TestDo.doSome(input);
                        System.out.println(Thread.currentThread().getName() + ":" + output);
                        semaphore.release();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }

        System.out.println("begin:" + (System.currentTimeMillis() / 1000));
        // 这行不能改动
        for (int i = 0; i < 10; i++) {
            // 这行不能改动
            String input = i + "";
            try {
                queue.put(input);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
