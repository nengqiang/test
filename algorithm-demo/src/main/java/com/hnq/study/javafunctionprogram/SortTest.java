package com.hnq.study.javafunctionprogram;

import com.google.common.collect.Lists;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @author henengqiang
 * @date 2018/10/11
 */
public class SortTest {

    public static void main(String[] args) {
        test1();
    }

    private static void test1() {
        List<String> list1 = Arrays.asList("alice", "dad", "candy", "zul", "handy");
//        list1.sort((e1, e2) -> e1.compareTo(e2));
        list1.sort(String::compareTo);
        list1.forEach(System.out::println);

        List<String> list2 = Lists.newArrayList();
        list2.addAll(list1);
//        list2.sort((e1, e2) -> e1.length() - e2.length());
        list2.sort(Comparator.comparingInt(String::length));
        list2.forEach(System.out::println);

        List<String> list3 = Lists.newArrayList();
        list3.addAll(list1);
//        Comparator<String> sortByLength = (String e1, String e2) -> e1.length() - e2.length();
        Comparator<String> sortByLength = Comparator.comparingInt(String::length);
        list3.sort(sortByLength);
        list3.forEach(System.out::println);
    }

}
