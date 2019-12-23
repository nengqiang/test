package com.hnq.practice.singlepattern.practice02;

/**
 * 懒汉式
 *
 * @author henengqiang
 * @date 2019/09/26
 */
public class Singleton04 {

    private static Singleton04 instance;

    private Singleton04() {
    }

    public static Singleton04 getInstance() {
        if (instance == null) {
            instance = new Singleton04();
        }
        return instance;
    }
}
