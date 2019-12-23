package com.hnq.practice.singlepattern.practice02;

/**
 * 饿汉式
 *
 * @author henengqiang
 * @date 2019/09/26
 */
public class Singleton01 {

    public static final Singleton01 INSTANCE = new Singleton01();

    private Singleton01() {
    }
}
