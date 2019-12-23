package com.hnq.study.lintcode;

/**
 * @author henengqiang
 * @date 2019/09/06
 */
public class Fibonacci {

    public static void main(String[] args) {
        System.out.println(fibonacci(1));
        System.out.println(fibonacci(2));
        System.out.println(fibonacci(6));
        System.out.println(fibonacci(10));
        System.out.println(fibonacci(41));
        System.out.println(fibonacci2(1));
        System.out.println(fibonacci2(2));
        System.out.println(fibonacci2(3));
        System.out.println(fibonacci2(4));
        System.out.println(fibonacci2(41));
    }

    /**
     * 超时
     */
    private static int fibonacci(int n) {

        if (n <= 1) {
            return 0;
        }
        if (n == 2) {
            return 1;
        }

        return fibonacci(n - 1) + fibonacci(n - 2);
    }

    private static int fibonacci2(int n) {
        if (n <= 1) {
            return 0;
        }
        if (n <= 3) {
            return 1;
        }

        /*
         * 用for循环代替递归能减少运行时间
         */

        int i1 =1, i2 = 1;
        int in = 0;
        for (int i = 4; i <= n; i++) {
            in = i1 + i2;
            i2 = i1;
            i1 = in;
        }
        return in;
    }

}
