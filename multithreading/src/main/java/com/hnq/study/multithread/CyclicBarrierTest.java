package com.hnq.study.multithread;

import com.hnq.toolkit.util.ThreadPoolUtils;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * CyclicBarrier循环路障
 *
 * 用通俗的话解释，表示大家彼此等待，大家集合好后才开始出发，分散活动后又在指定地点集合碰面，这就好比整个公司的人员
 * 利用周末时间集体郊游一样，先各自从家出发到公司集合后，再同时出发到公园游玩，然后在指定地点集合后再同时开始就餐的场景。
 *
 * CyclicBarrier是一个同步辅助类，它允许一组线程互相等待，直到到达某个公共屏障点 (common barrier point)。
 * 在涉及一组固定大小的线程的程序中，这些线程必须不时地互相等待，此时 CyclicBarrier 很有用。
 * 因为该barrier 在释放等待线程后可以重用，所以称它为循环的barrier。
 *
 * @author henengqiang
 * @date 2019/05/30
 */
public class CyclicBarrierTest {

    private static final CyclicBarrier CB = new CyclicBarrier(3);

    private static Random r = new Random();

    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            ThreadPoolUtils.execute(new Task());
        }
        ThreadPoolUtils.getThreadPool().shutdown();
    }

    private static class Task implements Runnable {
        @Override
        public void run() {
            try {
                goAhead();

                goAhead();

                goAhead();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        private void goAhead() throws InterruptedException, BrokenBarrierException {
            Thread.sleep(r.nextInt(1000));
            System.out.println("线程" + Thread.currentThread().getName() + "即将到达集合点1，当前已有"
                    + (CB.getNumberWaiting() + 1) + "个已经到达，"
                    + (CB.getNumberWaiting() == 2 ? "都到齐了，继续走啊..." : "正在等候"));

            CB.await();
        }
    }

}
