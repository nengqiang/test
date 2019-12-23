package com.hnq.practice.strategypattern;

import com.hnq.practice.strategypattern.bean.CalculateSecretkey;
import com.hnq.practice.strategypattern.bean.Printer;
import com.hnq.practice.strategypattern.service.impl.MD5StrategyImpl;

/**
 * @author henengqiang
 * @date 2018/11/20
 */
public class Client {

    public static void main(String[] args) {
        print();
        calcSecStr();
    }

    private static void print() {
        Printer printer = new Printer();

        printer.printShape("circle");
        printer.printShape("rectangle");
        printer.printShape("square");
    }

    private static void calcSecStr() {
        CalculateSecretkey secretKey = new CalculateSecretkey(new MD5StrategyImpl());
        String hash = secretKey.calcSecStr("1234567");
        System.out.println(hash);
    }

}
