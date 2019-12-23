package com.hnq.study.zklock.utils;

/**
 * @author henengqiang
 * @date 2019/12/17
 */
public class OrderNumUtil {

    private static int number = 0;

    public static String getOrderNum() {
        return String.valueOf(++number);
    }

}
