package com.hnq.practice.strategypattern.service.impl;

import com.hnq.practice.strategypattern.service.IPrintService;

/**
 * @author henengqiang
 * @date 2018/11/20
 */
public class SquareImpl implements IPrintService {

    @Override
    public void printShape() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 20; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }

}
