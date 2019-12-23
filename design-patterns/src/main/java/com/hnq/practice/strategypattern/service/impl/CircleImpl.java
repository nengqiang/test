package com.hnq.practice.strategypattern.service.impl;

import com.hnq.practice.strategypattern.service.IPrintService;

/**
 * @author henengqiang
 * @date 2018/11/20
 */
public class CircleImpl implements IPrintService {

    @Override
    public void printShape() {
        int radius = 10;
        for (int k = 0; k <= 2 * radius; k += 2) {
            int x = (int) Math.round(radius - Math.sqrt(radius * radius - (radius - k) * (radius - k)));
            int l = 2 * (radius - x);
            for (int i = 0; i <= x; i++) {
                System.out.print(" ");
            }
            System.out.print(".");
            for (int i = 0; i <= l; i++) {
                System.out.print("*");
            }
            System.out.println("*");
        }
    }

}
