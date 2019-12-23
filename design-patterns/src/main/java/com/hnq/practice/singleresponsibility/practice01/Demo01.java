package com.hnq.practice.singleresponsibility.practice01;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * @author henengqiang
 * @date 2019/08/20
 */
public class Demo01 {

    public static void main(String[] args) {
        List<Integer> test = Lists.newArrayList();
        test.add(1);
        test.add(2);
        for (Integer integer : test) {
            test.add(3);
            System.out.println(integer);
        }
    }

}

class Vehicle {
    /**
     * run方法违反单一职责原则
     * @param vehicle
     */
    public void run(String vehicle) {
        System.out.println(vehicle + "在公路上运行");
    }
}