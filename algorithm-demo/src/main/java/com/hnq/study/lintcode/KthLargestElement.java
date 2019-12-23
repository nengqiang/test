package com.hnq.study.lintcode;

/**
 * @author henengqiang
 * @date 2019/09/18
 */
public class KthLargestElement {

    public static void main(String[] args) {
        int[] nums = new int[]{9, 3, 2, 4, 8};
        System.out.println(kthLargestElement(3, nums));
    }

    private static int kthLargestElement(int n, int[] nums) {
        selectSort(nums);
        return nums[n - 1];
    }

    private static void selectSort(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            int maxIndex = i;
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[maxIndex] < nums[j]) {
                    maxIndex = j;
                }
            }
            if (maxIndex != i) {
                int temp = nums[i];
                nums[i] = nums[maxIndex];
                nums[maxIndex] = temp;
            }
        }
    }

}
