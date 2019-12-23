package com.hnq.practice.singlepattern.practice02;

/**
 * 懒汉式
 *
 * @author henengqiang
 * @date 2019/09/26
 */
public class Singleton05 {

    private static Singleton05 instance;

    private Singleton05() {
    }

    public static Singleton05 getInstance() {
        if (instance == null) {
            synchronized (Singleton05.class) {
                if (instance == null) {
                    instance = new Singleton05();
                }
            }
        }
        return instance;
    }
}
