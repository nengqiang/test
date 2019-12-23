package com.hnq.study.multithread;

import com.hnq.toolkit.util.ThreadPoolUtils;
import lombok.Setter;

import java.util.Random;
import java.util.concurrent.Exchanger;

/**
 * Exchanger同步工具
 *
 * 用于实现两个人之间的数据交换，每个人在完成一定的事务后想与对方交换数据，
 * 第一个先拿出数据的人将一直等待第二个人拿着数据到来时，才能彼此交换数据。
 *
 * 比喻:
 * 好比两个毒贩要进行交易，一手交钱，一手交货，不管谁先来到接头地点，都要处于等待
 * 状态，当另一方也到达了接头地点（所谓到达接头地点，也就到达了准备接头的状态）时，
 * 两者的数据立即交换了，然后又可以各忙各的了。
 *
 * exchange方法就相当与两手高高举着等待交换物，等待人家前来交换，一旦人家到来（即人家
 * 也执行到exchange方法），则两者立马完成数据的交换。
 *
 * @author henengqiang
 * @date 2019/05/31
 */
public class ExchangerTest {

    private static Exchanger<String> exchanger = new Exchanger<>();

    private static Random r = new Random();

    public static void main(String[] args) {
        Task task1 = new Task();
        task1.setData("123");
        Task task2 = new Task();
        task2.setData("456");
        ThreadPoolUtils.execute(task1);
        ThreadPoolUtils.execute(task2);
        ThreadPoolUtils.getThreadPool().shutdown();
    }

    private static class Task implements Runnable{
        @Setter
        private String data;
        @Override
        public void run() {
            try {
                System.out.println("线程" + Thread.currentThread().getName() + "正在把数据" + data + "换出去");
                Thread.sleep(r.nextInt(2000));
                String data2 = exchanger.exchange(data);
                System.out.println("线程" + Thread.currentThread().getName() + "换回的数据为" + data2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
