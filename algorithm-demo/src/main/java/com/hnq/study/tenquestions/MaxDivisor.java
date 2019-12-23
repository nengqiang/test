package com.hnq.study.tenquestions;

/**
 * 求最大公约数
 * 算法：是两个数相余，直到余数为0，如果余数不为0，就用除数和余数求余
 *
 * @author henengqiang
 * @date 2018/12/12
 */
public class MaxDivisor {

    public static void main(String[] args) {
        int maxDivisor = getMaxDivisor(3, 2);
        System.out.println("Max Divisor of 3 and 2 is " + maxDivisor);
        maxDivisor = getMaxDivisor(10, 8);
        System.out.println("Max Divisor of 10 and 8 is " + maxDivisor);
    }

    private static int getMaxDivisor(int x1, int x2) {
        int remainder = x1 % x2;
        if (remainder == 0) {
            return x2;
        } else {
            return getMaxDivisor(x2, remainder);
        }
    }

}
