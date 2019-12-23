package com.hnq.study.tenquestions;

/**
 * 罗马数字倒转成阿拉伯数字
 *
 * @author henengqiang
 * @date 2018-12-11
 */
public class RomanToNumber {

    /** 定义罗马数字 */
    private static final char[] DIGITS = {'I', 'V', 'X', 'L', 'C', 'D', 'M'};

    /** 罗马数字对应的阿拉伯数字 */
    private static final int[] VALUES = {1, 5, 10, 50, 100, 500, 1000};

    public static void main(String[] args) {
        int number = romanToNumber("XVD", 0, 2);
        System.out.println(number);
    }

    private static int romanToNumber(String romanNum, int left, int right) {
        // 如果只有一个罗马数字，那么可以直接返回了(递归出口)
        if (left == right) {
            return digitsToValues(romanNum.charAt(left));
        } else if (left > right) {
            // 如果L和R已经越界了，那么说明没有值了
            return 0;
        } else {
            // 找到当前罗马数字最大值的角标
            int maxIndex = findMaxIndex(romanNum, left, right);

            // 得到最大值　
            int max = digitsToValues(romanNum.charAt(maxIndex));

            // 在最大值左边的，则用最大值减去左边的
            int leftNum = romanToNumber(romanNum, left, maxIndex - 1);

            //在最大值右边的，则用最大值加上右边的
            int rightNum = romanToNumber(romanNum, maxIndex + 1, right);

            return max - leftNum + rightNum;
        }
    }

    /**
     * 获取单个罗马数字对应的阿拉伯数字
     * @param roman 罗马数字
     * @return      返回阿拉伯数字
     */
    private static int digitsToValues(char roman) {
        for (int i = 0; i < DIGITS.length; i++) {
            if (DIGITS[i] == roman) {
                return VALUES[i];
            }
        }
        return 0;
    }

    private static int findMaxIndex(String digits, int left, int right) {
        // 假设第一个是最大的
        int max = digitsToValues(digits.charAt(left));
        int maxIndex = left;
        for (int i = left; i < right; i++) {
            // 将罗马数字转换成阿拉伯数字
            int num = digitsToValues(digits.charAt(i));
            if (max < num) {
                max = num;
                maxIndex = i;
            }
        }
        return maxIndex;
    }

}
