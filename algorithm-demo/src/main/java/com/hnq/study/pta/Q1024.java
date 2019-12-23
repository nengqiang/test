package com.hnq.study.pta;

import java.util.Scanner;

/**
 * @author henengqiang
 * @date 2019/11/07
 */
public class Q1024 {

    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        logic();
    }

    /**
     * 部分正确
     */
    private static void logic() {
        String in = sc.nextLine();
        String[] ins = in.split("E");
        double a = Double.parseDouble(ins[0].replace("+", ""));
        int b = Integer.parseInt(ins[1].replace("+", ""));
        String res = "";
        StringBuilder k = new StringBuilder();
        String tmp = ins[0].replace("+", "").replace(".", "");
        if (a > 0 && b > 0) {
            if (b > ins[0].split("\\.")[1].length()) {
                for (int i = 0; i < b; i++) {
                    k.append("0");
                }
                res = tmp + k;
            } else {
                res = tmp.substring(0, b) + "." + tmp.substring(b + 1);
            }
        }
        if (a > 0 && b < 0) {
            for (int i = 0; i < Math.abs(b) - 1; i++) {
                k.append("0");
            }
            res = "0." + k + tmp;
        }
        if (a < 0 && b > 0) {
            if (b > ins[0].split("\\.")[1].length()) {
                for (int i = 0; i < b; i++) {
                    k.append("0");
                }
                res = tmp + k;
            } else {
                res = tmp.substring(0, b + 1) + "." + tmp.substring(b + 2);
            }
        }
        if (a < 0 && b < 0) {
            for (int i = 0; i < Math.abs(b) - 1; i++) {
                k.append("0");
            }
            res = "-0." + k + tmp.replace("-", "");
        }
        System.out.println(res);
    }

}
