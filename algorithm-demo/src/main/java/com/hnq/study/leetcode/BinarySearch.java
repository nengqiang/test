package com.hnq.study.leetcode;

/**
 *  二分查找
 * 中文English
 * 给定一个排序的整数数组（升序）和一个要查找的整数target，用O(logn)的时间查找到target第一次出现的下标（从0开始），
 * 如果target不存在于数组中，返回-1。
 *
 * 样例
 * 样例  1:
 * 	输入:[1,4,4,5,7,7,8,9,9,10]，1
 * 	输出: 0
 *
 * 	样例解释:
 * 	第一次出现在第0个位置。
 *
 * 样例 2:
 * 	输入: [1, 2, 3, 3, 4, 5, 10]，3
 * 	输出: 2
 *
 * 	样例解释:
 * 	第一次出现在第2个位置
 *
 * 样例 3:
 * 	输入: [1, 2, 3, 3, 4, 5, 10]，6
 * 	输出: -1
 *
 * 	样例解释:
 * 	没有出现过6， 返回-1
 *
 * 挑战
 * 如果数组中的整数个数超过了2^32，你的算法是否会出错？
 *
 * @author henengqiang
 * @date 2020/3/17
 */
public class BinarySearch {

    public static void main(String[] args) {
        int[] test1 = new int[]{1, 4, 4, 5, 7, 7, 8, 9, 9, 10};
        int[] test2 = new int[]{1, 2, 3, 3, 4, 5, 10};
        int[] test3 = new int[]{1, 2, 3, 3, 4, 5, 10};
        System.out.println(binarySearch(test1, 1));
        System.out.println(binarySearch(test2, 3));
        System.out.println(binarySearch(test3, 6));

        int[] test4 = new int[]{0};
        System.out.println(binarySearch(test4, 0));
    }

    private static int binarySearch(int[] nums, int target) {
         return search(nums, target, 0, nums.length - 1);
    }

    private static int search(int[] nums, int target, int start, int end) {
        if (nums != null && nums.length == 1) {
            return nums[0] == target ? 0 : -1;
        }
        if (nums == null || nums.length <= 0 || start == end) {
            return -1;
        }
        int pos = (end - start) / 2 + start;
        if (target < nums[pos]) {
            return search(nums, target, start, pos);
        }
        if (target > nums[pos]) {
            return search(nums, target, pos + 1, end);
        }
        int tmp = search(nums, target, start, pos);
        return tmp == -1 ? pos : tmp;
    }

}
