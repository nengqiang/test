package com.hnq.practice.singlepattern.practice02;

/**
 * 在内部类被加载和初始化时，才创建INSTANCE实例对象
 *
 * 延迟创建 + 线程安全
 *
 * @author henengqiang
 * @date 2019/09/26
 */
public class Singleton06 {

    private Singleton06() {
    }

    private static class Inner {

        private static final Singleton06 INSTANCE = new Singleton06();

        public static Singleton06 getInstance() {
            return Inner.INSTANCE;
        }

    }

}
