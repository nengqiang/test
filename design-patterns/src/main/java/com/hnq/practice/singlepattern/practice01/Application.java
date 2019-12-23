package com.hnq.practice.singlepattern.practice01;

/**
 * @author henengqiang
 * @date 2019/03/15
 */
public class Application {

    public static void main(String[] args) {
        singletonTest();
    }

    private static void singletonTest() {
        Singleton singleton1 = Singleton.getInstance();
        Singleton singleton2 = Singleton.getInstance();
        System.out.println(singleton1 + "\n" + singleton2);
        System.out.println(singleton1 == singleton2);
    }

}
