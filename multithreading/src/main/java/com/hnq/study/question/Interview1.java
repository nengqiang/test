package com.hnq.study.question;

import com.google.common.collect.Queues;
import com.hnq.toolkit.util.ThreadPoolUtils;
import lombok.Setter;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 现有的程序代码模拟产生了16个日志对象，并且需要运行16秒才能打印完这些日志，请在程序
 * 中增加4个线程去调用parseLog()方法来分头打印这16个日志对象，程序只需要运行4秒即可打印
 * 玩这些日志对象。
 *
 * @author henengqiang
 * @date 2019/06/03
 * @see <a href="https://blog.csdn.net/acmman/article/details/53116117">Answer</a>
 */
public class Interview1 {

    // --- 原始代码如下 --- //

    public static void main(String[] args) {
        System.out.println("begin:" + (System.currentTimeMillis() / 1000));
        /*模拟处理16行日志，下面的代码产生了16条日志对象，
         * 当前代码需要运行16秒才能打印完这些日志*/
        for (int i = 0; i < 16; i++) {
            //这行代码不能改动
            final String log = "" + (i + 1);
            {
                parseLog(log);
            }
        }
    }

    /**
     * parseLog方法内的代码不能改动
     */
    public static void parseLog(String log) {
        System.out.println(log + ":" + (System.currentTimeMillis() / 1000));
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

class Q1Answer1 {
    public static void main(String[] args) {
        System.out.println("begin:" + (System.currentTimeMillis() / 1000));
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                final String log = "" + (j + 1);
                ParseLog parseLog = new ParseLog();
                parseLog.setLog(log);
                ThreadPoolUtils.execute(parseLog);
            }
        }
        ThreadPoolUtils.shutdown();
    }

    private static class ParseLog implements Runnable {
        @Setter
        private String log;

        @Override
        public void run() {
            Interview1.parseLog(log);
        }
    }
}

class Q1Answer2 {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(4);
        System.out.println("begin:" + (System.currentTimeMillis() / 1000));
        for (int i = 0; i < 16; i++) {
            //这行代码不能改动
            final String log = "" + (i + 1);
            {
                // 改为lambda后无法按照顺序打印？？？
                executor.execute(new Runnable() {
                    @Override
                    public void run() {
                        Interview1.parseLog(log);
                    }
                });
            }
        }
        executor.shutdown();
    }
}

class Q1OfficialAnswer {
    public static void main(String[] args) {
        final BlockingQueue<String> queue = Queues.newArrayBlockingQueue(4);
        for (int i = 0; i < 4; i++) {
            new Thread(() -> {
                String log;
                while (true) {
                    try {
                        if (queue.size() > 0) {
                            log = queue.take();
                            Interview1.parseLog(log);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }

        System.out.println("begin:" + (System.currentTimeMillis() / 1000));
        for (int i = 0; i < 16; i++) {
            //这行代码不能改动
            final String log = "" + (i + 1);
            try {
                queue.put(log);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}