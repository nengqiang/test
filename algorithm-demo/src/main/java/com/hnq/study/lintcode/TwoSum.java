package com.hnq.study.lintcode;

import java.util.Arrays;

/**
 * @author henengqiang
 * @date 2019/09/17
 */
public class TwoSum {

    public static void main(String[] args) {
        int[] t = new int[]{2, 7, 11, 15};
        System.out.println(Arrays.toString(twoSum(t, 9)));
        t = new int[]{15, 2, 7, 11};
        System.out.println(Arrays.toString(twoSum(t, 9)));
        System.out.println(Arrays.toString(twoSum(t, 10)));
    }

    private static int[] twoSum(int[] numbers, int target) {
        int i = 0, j = numbers.length - 1;
        while (i < j) {
            if (numbers[i] + numbers[j] < target) {
                i++;
            }
            if (numbers[i] + numbers[j] > target) {
                j--;
            }
            if (numbers[i] + numbers[j] == target) {
                return new int[]{i, j};
            }
        }
        return null;
    }


}
