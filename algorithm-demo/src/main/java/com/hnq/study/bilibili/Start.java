package com.hnq.study.bilibili;

import static com.google.common.math.IntMath.factorial;

/**
 * @author henengqiang
 * @date 2020/3/12
 */
public class Start {

    public static void main(String[] args) {
        int n = 1000;
        System.out.println("Output: " + n);
        System.out.println("Output2: " + n);
        System.out.println("Output3: " + n);

        for (int i = 0; i < n; i++) {
            System.out.println("Output: " + i);
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.println("Output1: " + i + " Output2: " + j);
            }
        }

        for (int i = 0; i < n; i *= 2) {
            System.out.println("Output: " + i);
        }

        for (int i = 0; i < Math.pow(2, n); i++) {
            System.out.println("Output: " + i);
        }

        /**
         * factorial(n) = n!
         */
        for (int i = 0; i < factorial(n); i++) {
            System.out.println("Output: " + i);
        }


    }

    private static int fib(int n) {
        if (n == 0 || n == 1) {
            return n;
        }
        return fib(n - 1) + fib(n - 2);
    }

}
