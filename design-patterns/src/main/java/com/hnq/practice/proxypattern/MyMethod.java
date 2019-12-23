package com.hnq.practice.proxypattern;

/**
 * @author henengqiang
 * @date 2019/02/27
 */
public class MyMethod {

    public void sing() {
        System.out.println("sing~~~");
    }

    public void song(String name) {
        System.out.println("song~~~" + name);
    }

    public void go() {
        System.out.println("gogogo!");
    }

    public void back(String str) {
        System.out.println("back: " + str);
    }

}
