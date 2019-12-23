package com.hnq.study.multithread;

import com.hnq.toolkit.util.ThreadPoolUtils;
import lombok.Data;

import java.util.Random;

/**
 * ThreadLocal类及应用技巧
 *
 * @author henengqiang
 * @date 2019/05/17
 */
public class ThreadShare04 {

    private static Random r = new Random();

    public static void main(String[] args) {
        for (int i = 0; i < 2; i++) {
            ThreadPoolUtils.execute(new Runnable() {
                @Override
                public void run() {
                    putData();
                    getData("A");
                    getData("B");
                }
            });
        }
        ThreadPoolUtils.getThreadPool().shutdown();
        MyThreadScopeData.threadLocal.remove();
    }

    private static void putData() {
        int data = r.nextInt();
        System.out.println(Thread.currentThread().getName() + "放入数据:" + data);
        MyThreadScopeData.getInstance().setName("name" + data);
        MyThreadScopeData.getInstance().setAge(data);
    }

    private static void getData(String name) {
        System.out.println(name + "从" + Thread.currentThread().getName() + "中取的数据:"
                + MyThreadScopeData.getInstance().getName() + " " + MyThreadScopeData.getInstance().getAge());
    }

    @Data
    private static class MyThreadScopeData {
        private String name;
        private Integer age;
        private static ThreadLocal<MyThreadScopeData> threadLocal = new ThreadLocal<MyThreadScopeData>();
        private MyThreadScopeData() {
        }
        MyThreadScopeData(String name, Integer age) {
            this.name = name;
            this.age = age;
        }
        static MyThreadScopeData getInstance() {
            MyThreadScopeData instance = threadLocal.get();
            if (instance == null) {
                instance = new MyThreadScopeData();
                threadLocal.set(instance);
            }
            return instance;
        }
    }

}
