package com.hnq.study.tenquestions;

import java.util.Arrays;

/**
 * 将0放在数组最后
 *
 * 思路：
 *  使用一个变量zero来记住该数组有多少个0 遍历这个数组，
 *  如果发现不是0的，就往数组前面移动，如果发现是0就zero++
 *  数组移动的位置刚好是arr[i-zero]的
 *
 * @author henengqiang
 * @date 2018-12-11
 */
public class MoveZero {

    public static void main(String[] args) {
        int[] array = new int[]{1, 0, 3, 4, 0, 9, 7, 3, 0, 0, 0, 5, 6};
        System.out.println("The Origin Array: " + Arrays.toString(array));
        int[] newArray = moveZero(array);
        System.out.println("The Array After: " + Arrays.toString(newArray));
    }

    private static int[] moveZero(int[] array) {
        // 记录该数组有多少个0元素
        int zero = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == 0) {
                // 如果为0，那么zero ++
                zero++;
            } else {
                // 只要元素不为0，那么就往前面移动
                array[i - zero] = array[i];
            }
        }

        // 1. 前面已经将非0的元素移动到数组的前面了
        // 2. 将为0的元素填满数组，填充的位置就从length - zero开始

        for (int i = array.length - zero; i < array.length; i++) {
            array[i] = 0;
        }
        return array;
    }

}
