package com.hnq.study.tradition;

import com.hnq.toolkit.consts.DateConsts;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 传统定时器
 *
 * @author henengqiang
 * @date 2019/05/16
 */
public class TimerTest {

    public static void main(String[] args) {
        aTimer();
        manyTimers();
        recursiveTimer();
    }

    /**
     * 只执行了一次就结束了
     */
    private static void aTimer() {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("aTimer: " + getNowTimeStr());
            }
        }, 1000);
    }

    /**
     * 每隔一秒就执行一次
     */
    private static void manyTimers() {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("manyTimers: " + getNowTimeStr());
            }
        }, 1000, 1000);
    }

    /**
     * 用递归实现每隔一秒就执行一次
     */
    private static void recursiveTimer() {
        new Timer().schedule(new MyTimerTask(), 1000);
    }

    private static String getNowTimeStr() {
        return DateFormatUtils.format(new Date(), DateConsts.DEFAULT_PATTERN);
    }

    static class MyTimerTask extends TimerTask {
        @Override
        public void run() {
            System.out.println("MyTimerTask: " + getNowTimeStr());
            new Timer().schedule(new MyTimerTask(), 1000);
        }
    }

}
