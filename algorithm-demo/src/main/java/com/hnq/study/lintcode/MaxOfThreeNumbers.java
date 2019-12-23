package com.hnq.study.lintcode;

/**
 * @author henengqiang
 * @date 2019/09/06
 */
public class MaxOfThreeNumbers {

    public static void main(String[] args) {
        System.out.println(maxOfThreeNumbers(1, 2, 3));
        System.out.println(maxOfThreeNumbers(4, 5, 6));
    }

    private static int maxOfThreeNumbers(int num1, int num2, int num3) {
        int max1 = num1 > num2 ? num1 : num2;
        return max1 > num3 ? max1 : num3;
    }

}
