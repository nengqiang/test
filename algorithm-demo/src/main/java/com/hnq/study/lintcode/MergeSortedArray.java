package com.hnq.study.lintcode;

import java.util.Arrays;

/**
 * @author henengqiang
 * @date 2019/09/05
 */
public class MergeSortedArray {

    public static void main(String[] args) {
        int[] a = new int[]{1, 3, 5, 7, 9};
        int[] b = new int[]{2, 4, 6, 8, 10};
        int[] r = mergeSortedArray(a, b);
        System.out.println(Arrays.toString(r));
    }

    private static int[] mergeSortedArray(int[] A, int[] B) {
        int[] result = new int[A.length + B.length];
        for (int i = 0; i < A.length; i++) {
            result[i] = A[i];
        }
        int k = 0;
        for (int i = A.length; i < A.length + B.length; i++) {
            result[i] = B[k++];
        }

        sort(result);

        return result;
    }

    /**
     * 简单选择排序
     */
    private static void sort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[minIndex] > arr[j]) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                int temp = arr[i];
                arr[i] = arr[minIndex];
                arr[minIndex] = temp;
            }
        }
    }

}
