package com.hnq.practice.singlepattern.practice01;

/**
 * @author henengqiang
 * @date 2019/03/15
 */
public class Singleton {
    private static Singleton ourInstance = new Singleton();

    public static Singleton getInstance() {
        return ourInstance;
    }

    private Singleton() {
    }
}
