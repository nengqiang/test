package com.hnq.practice.singlepattern.practice02;
import com.hnq.toolkit.util.ThreadPoolUtils;

import java.util.concurrent.ExecutionException;
import	java.util.concurrent.Future;

import java.util.concurrent.Callable;

/**
 * @author henengqiang
 * @date 2019/09/26
 */
public class Client {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        test01();
        test02();
        test03();
        test04();
    }

    private static void test01() {
        Singleton01 s1 = Singleton01.INSTANCE;
        System.out.println(s1);
    }

    private static void test02() {
        System.out.println(Singleton02.INSTANCE.getDeclaringClass().toString());
    }

    private static void test03() {
        Singleton03 s3 = Singleton03.INSTANCE;
        System.out.println(s3.getInfo());
    }

    private static void test04() throws ExecutionException, InterruptedException {
        /*Singleton04 s41 = Singleton04.getInstance();
        Singleton04 s42 = Singleton04.getInstance();
        System.out.println(s41 == s42);
        System.out.println(s41);
        System.out.println(s42);*/


        Callable<Singleton04> c = Singleton04::getInstance;
        Future<Singleton04> f1 = ThreadPoolUtils.submit(c);
        Future<Singleton04> f2 = ThreadPoolUtils.submit(c);

        Singleton04 s43 = f1.get();
        Singleton04 s44 = f2.get();

        System.out.println(s43 == s44);
        System.out.println(s43);
        System.out.println(s44);

        ThreadPoolUtils.shutdown();
    }



}
