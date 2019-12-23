package com.hnq.study.lintcode;

import java.util.Arrays;

/**
 * @author henengqiang
 * @date 2019/09/05
 */
public class SwapIntegers {

    public static void main(String[] args) {
        int[] arr = new int[] {1, 2, 3, 4};
        swapIntegers(arr, 0, 1);
        System.out.println(Arrays.toString(arr));
    }

    private static void swapIntegers(int[] A, int index1, int index2) {
        int temp = A[index1];
        A[index1] = A[index2];
        A[index2] = temp;
    }

}
