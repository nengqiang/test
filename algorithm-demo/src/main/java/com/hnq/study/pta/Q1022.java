package com.hnq.study.pta;

import java.math.BigInteger;
import java.util.Scanner;

/**
 * @author henengqiang
 * @date 2019/10/31
 */
public class Q1022 {

    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        String in = sc.nextLine();
        String[] ins = in.split("\\s");
        int res = logic(new BigInteger(ins[0]).add(new BigInteger(ins[1])), new BigInteger(ins[2]));
        System.out.println(res);
    }

    /**
     * 部分正确
     */
    private static int logic(BigInteger num, BigInteger radix) {
        //使用StringBuilder的reverse方法
        StringBuilder sb = new StringBuilder();

        while (num.compareTo(BigInteger.ZERO) > 0) {
            sb.append(num.mod(radix));
            num  = num.divide(radix);
        }

        return Integer.parseInt(sb.reverse().toString());
    }

}
