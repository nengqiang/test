package com.hnq.practice.strategypattern.service.impl;

import com.hnq.practice.strategypattern.service.IPrintService;

/**
 * @author henengqiang
 * @date 2018/11/20
 */
public class RectangleImpl implements IPrintService {

    @Override
    public void printShape() {
        int line = 10;
        for (int i = 0; i <= line; i++) {
            for (int j = line; j > i; j--) {
                System.out.print(" ");
            }
            for (int j = 0; j < 2 * i - 1; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }

}
