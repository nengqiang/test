package com.hnq.study.multithread;

import com.hnq.toolkit.util.ThreadPoolUtils;

/**
 * 多个线程之间共享数据
 *
 * 给数据对象的操作方法加锁
 *
 * @author henengqiang
 * @date 2019/05/17
 */
public class ThreadsShare {

    private static Data data = new Data();

    public static void main(String[] args) {
        data.setJ(0);
        System.out.println("j=" + 0);
        R1 a = new R1(data);
        R2 b = new R2(data);
        ThreadPoolUtils.execute(a);
        ThreadPoolUtils.execute(a);
        ThreadPoolUtils.execute(b);
        ThreadPoolUtils.execute(b);
        ThreadPoolUtils.getThreadPool().shutdown();
    }

    private static class R1 implements Runnable {

        private Data data;

        R1(Data data) {
            this.data = data;
        }

        @Override
        public void run() {
            data.add();
        }
    }

    private static class R2 implements Runnable {

        private Data data;

        R2(Data data) {
            this.data = data;
        }

        @Override
        public void run() {
            data.minus();
        }
    }

    private static class Data {
        private int j;

        void setJ(int j) {
            this.j = j;
        }

        synchronized void add() {
            j++;
            System.out.println(Thread.currentThread().getName() + ": j+1=" + j);
        }

        synchronized void minus() {
            j--;
            System.out.println(Thread.currentThread().getName() + ": j-1=" + j);
        }
    }

}
