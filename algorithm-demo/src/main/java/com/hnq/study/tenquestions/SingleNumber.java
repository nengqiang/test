package com.hnq.study.tenquestions;

/**
 * 给一个数组，除了一个数字外，其他的数字都出现了两次，请把这个只出现一次的数字找出来。
 *
 * 思路：
 *  将该数组遍历一次，记录每个数字出现的次数
 *  如果该数字出现的次数只有1，那么该数字就是单个数字～
 *
 * 优化：
 *  这个问题最佳的解法是用到了位运算的异或操作：
 *  如果5^5=0
 *  如果5^7^5 = 7
 *  如果5^6^6^5^7^8^7 = 8
 *  从上面的例子可以看出：一堆数字做异或运算^，俩俩相同数字就会被抵消掉～，所以这个特性对于这个题目而言就再适合不过的了：
 *
 * @author henengqiang
 * @date 2018-12-11
 */
public class SingleNumber {

    public static void main(String[] args) {
        int[] array = new int[]{1, 2, 3, 4, 5, 4, 3, 2, 1};
        int theNumber = findSingleNumberA(array);
        System.out.println("The Single Number is " + theNumber);
        int theSingleNumber = findSingleNumberB(array);
        System.out.println("The Single Number find by B is " + theSingleNumber);
    }

    private static int findSingleNumberA(int[] array) {
        int count;
        for (int arr : array) {
            count = countNumber(array, arr);
            if (count == 1) {
                return arr;
            }
        }
        return -1;
    }

    private static int countNumber(int[] array, int number) {
        int count = 0;
        for (int arr : array) {
            if (arr == number) {
                count++;
            }
        }
        return count;
    }

    private static int findSingleNumberB(int[] array) {
        int result = array[0];
        for (int i = 1; i < array.length; i++) {
            result = result ^ array[i];
        }
        return result;
    }

}
