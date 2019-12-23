package com.hnq.study.tenquestions;

import java.util.Arrays;
import java.util.Stack;

/**
 * 给一个长度为n的数组，其中有一个数字出现的次数至少为n/2，找出这个数字
 *
 * 这道题可以用栈的思想来做：
 *  如果栈是空的，那么先把数据存进去
 *  然后继续遍历其他的数据，只要发现栈中的数据和遍历中的数据不一样，那么就出栈
 *  如果是相同的，那么就入栈
 *  其实就是捉住数字出现的次数多于数组一半的长度这里入手。如果这个数出现的次数是大于这个数组长度的1/2，那么最后留下的肯定是这个数
 *
 * @author henengqiang
 * @date 2018-12-11
 */
public class FindMajorityElement {

    public static void main(String[] args) {
        int[] array = new int[]{1, 2, 3, 1, 2, 1, 1};
        int theNumber = findMajorityElement(array);
        System.out.println("The array is " + Arrays.toString(array));
        System.out.println("The majority number is " + theNumber);
    }

    private static int findMajorityElement(int[] array) {
        Stack<Integer> stack = new Stack<Integer>();
        for (int element : array) {
            if (stack.isEmpty()) {
                stack.push(element);
            }
            if (stack.peek() == element) {
                stack.push(element);
            } else {
                stack.pop();
            }
        }
        return stack.pop();
    }

}
