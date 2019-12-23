package com.hnq.study.zklock.service;

import java.util.stream.IntStream;

/**
 * @author henengqiang
 * @date 2019/12/17
 */
public class Client {

    public static void main(String[] args) {
        IntStream.rangeClosed(1, 20).forEach(i -> new Thread(() -> new OrderService().getOrderNum(), String.valueOf(i)).start());
    }

}
