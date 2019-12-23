package com.hnq.study.tenquestions;

import java.util.Arrays;

/**
 * 删除下标为k的元素
 * 思路：数组后一位往前覆盖即可～
 *
 * @author henengqiang
 * @date 2018-12-11
 */
public class DeleteK {

    public static void main(String[] args) {
        methodA(3);
    }

    private static void methodA(int k) {
        int[] array = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        if (k < 0 || k > array.length) {
            throw new IndexOutOfBoundsException("方法中数组长度为：" + array.length);
        }
        System.out.println("The Array before: " + Arrays.toString(array));

        for (int i = k; i < k - 1; i++) {
            array[k] = array[k + 1];
        }
        int[] theNewArray = new int[array.length - 1];
        System.arraycopy(array, 0, theNewArray, 0, theNewArray.length);
        System.out.println("The Array after: " + Arrays.toString(theNewArray));
    }

}
