package com.hnq.practice.commandpattern;

import java.net.URL;

/**
 * @author henengqiang
 * @date 2019/02/27
 */
public class CommandMain {

    public static void main(String[] args) {
        boolean b = Retry.execute(3, new RetryRunnable() {
            // 怎么做
            @Override
            public void run() {
                try {
                    URL url = new URL("http://www.ly.com");
                    System.out.println(url);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        System.out.println("执行结果： " + b);
    }

}
