package com.hnq.study.practice;
import	java.util.Iterator;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * 设有n个人依围成一圈，从第1个人开始报数，数到第m个人出列，然后从 出列的下一个人开始报数，数到第m个人又出列，...，
 * 如此反复到所有的人全部出列 为止。设n个人的编号分别为 1，2，...，n，打印出出列的顺序
 *
 * @author henengqiang
 * @date 2019/08/08
 */
public class CountGame {

    public static void main(String[] args) {
        countIt(10, 3);
        System.out.println("----------");
        countIt(11, 2);
    }

    private static void countIt(int n, int m) {
        List<Integer> list = Lists.newArrayList();
        for (int i = 0; i < n; i++) {
            list.add(i);
        }
        dequeueIt(list, m);
    }

    private static void dequeueIt(List<Integer> list, int m) {
        System.out.println(list);
        if (list.size() < m) {
            return;
        }
        int count = 0;
        Iterator<Integer> it = list.iterator();
        while (it.hasNext()) {
            count++;
            int dest = it.next();
            if (count == m) {
                count = 0;
                System.out.println(dest);
                it.remove();
            }
        }
        dequeueIt(list, m);
    }

}
