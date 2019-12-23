package com.hnq.study.multithread;

import com.hnq.toolkit.consts.BannerConsts;
import com.hnq.toolkit.util.ThreadPoolUtils;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

/**
 * 犹如倒计时计数器，调用CountDownLatch对象的countDown方法就将计数器减1，当计数器到达0时，
 * 则所有等待着或单个等待着开始执行。
 *
 * 可以实现一个人(也可以是多个人)等待其它所有人来通知他，也可以实现一个人通知多个人的
 * 效果，类似裁判一声口令，运动员同时开始奔跑，或者所有运动员都跑到终点后裁判才可以公
 * 布结果，这个功能适合做类似百米赛跑的功能。
 *
 * @author henengqiang
 * @date 2019/05/30
 */
public class CountDownLatchTest {

    private static final CountDownLatch CD_ORDER = new CountDownLatch(1);

    private static final CountDownLatch CD_ANSWER = new CountDownLatch(3);

    private static Random r = new Random();

    public static void main(String[] args) {
//        countDownTest();
        int num = 10;
        int test = 20;
        for (int i = 0; i < (test % num == 0 ? test / num : test / num + 1); i++) {
            if (test > num * (i + 1)) {
                System.out.println(num * i);
                System.out.println(num * (i + 1));
            } else {
                System.out.println(num * i);
                System.out.println(test);
            }
            System.out.println("----");
        }
    }

    private static void countDownTest() {
        for (int i = 0; i < 3; i++) {
            ThreadPoolUtils.execute(new AnswerTask());
        }
        try {
            Thread.sleep(r.nextInt(1000));

            System.out.println("线程" + Thread.currentThread().getName() + "即将发布命令");

            CD_ORDER.countDown();

            System.out.println("线程" + Thread.currentThread().getName() + "已发送命令，正在等待结果...");

            CD_ANSWER.await();

            System.out.println("线程"
                    + BannerConsts.genBannerOnline(BannerConsts.FONT_ALLIGATOR, Thread.currentThread().getName())
                    + "已收到所有响应");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ThreadPoolUtils.getThreadPool().shutdown();
    }

    private static class AnswerTask implements Runnable{
        @Override
        public void run() {
            try {
                System.out.println("线程" + Thread.currentThread().getName() + "正准备接收命令");

                CD_ORDER.await();

                System.out.println("线程" + Thread.currentThread().getName() + "已接收命令");

                Thread.sleep(r.nextInt(1000));

                System.out.println("线程" + Thread.currentThread().getName() + "回应命令处理结束");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                CD_ANSWER.countDown();
            }
        }
    }

}
