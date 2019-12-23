package com.hnq.study.leetcode;

import com.google.common.collect.Maps;

import java.util.Arrays;
import java.util.Map;

/**
 * @author henengqiang
 * @date 2019/03/18
 */
public class QuestionBank {

    public static void main(String[] args) {
//        System.out.println(Arrays.toString(twoSum(new int[] {1, 2, 3, 4}, 3)));
//        System.out.println(Arrays.toString(twoSum1(new int[] {1, 2, 3, 4}, 3)));
        ListNode listNode1 = new ListNode(1).setNext(new ListNode(2).setNext(new ListNode(3)));
        ListNode listNode2 = new ListNode(9).setNext(new ListNode(7).setNext(new ListNode(3)));
        System.out.println(addTwoNumbers(listNode1, listNode2));
    }

    private static int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[] {i, j};
                }
            }
        }
        return null;
    }

    private static int[] twoSum1(int[] nums, int target) {
        Map<Integer, Integer> map = Maps.newHashMap();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement) && map.get(complement) != i) {
                return new int[] {i, map.get(complement)};
            }
        }
        return null;
    }

    // -------------------------------

    private static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // 哑节点
        ListNode dummyHead = new ListNode(0);
        // p 和 q 分别为列表 l1 和 l2 的头部
        ListNode p = l1, q = l2, curr = dummyHead;
        // 进位
        int carry = 0;
        while (p != null || q != null) {
            int x = (p != null) ? p.getVal() : 0;
            int y = (q != null) ? q.getVal() : 0;
            int sum = carry + x + y;
            carry = sum / 10;
            curr.setNext(new ListNode(sum % 10));
            curr = curr.getNext();
            p = (p != null) ? p.getNext() : null;
            q = (q != null) ? q.getNext() : null;
            curr.setNext(carry > 0 ? new ListNode(carry) : null);
        }
        return dummyHead.getNext();
    }

}
