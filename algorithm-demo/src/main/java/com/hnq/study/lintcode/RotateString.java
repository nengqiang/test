package com.hnq.study.lintcode;

/**
 * @author henengqiang
 * @date 2019/09/05
 */
public class RotateString {

    public static void main(String[] args) {
        char[] a = new char[]{'a', 'b', 'c', 'd', 'e'};
        rotateString(a, 0);
        System.out.println(a);
        rotateString(a, 3);
        System.out.println(a);
        rotateString(a, 11);
        System.out.println(a);
    }

    private static void rotateString(char[] str, int offset) {
        if (str == null || str.length == 0 || offset < 0) {
            return;
        }
        if (offset > str.length) {
            offset = offset % str.length;
        }
        // length of first part
        int a = str.length - offset;

        reverse(str, 0, a - 1);
        reverse(str, a, str.length - 1);
        reverse(str, 0, str.length - 1);
    }

    private static void reverse(char[] arr, int left, int right) {
        if (arr == null || arr.length == 1) {
            return;
        }
        while (left < right) {
            char temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
            left++;
            right--;
        }
    }


}
