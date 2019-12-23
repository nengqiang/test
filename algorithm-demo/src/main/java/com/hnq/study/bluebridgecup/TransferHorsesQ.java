package com.hnq.study.bluebridgecup;

import com.hnq.toolkit.util.SortUtils;

import java.util.Scanner;

/**
 * 运送马匹
 *   有1个人，要把n匹马从A村运往B村。
 *   初始时，人和马都在A村。每次骑1匹马牵1匹马，回来时骑1匹马。
 *   已知每匹马从A村到B村需要的时间（数字越大越慢）
 *   两匹马同行时只能迁就较慢者。
 *   求所有马匹都运到B村的最小的运输时间（此时，人和马都在B村）。
 *   程序首先输入一个整数n(n<100)，表示有n匹马。
 *   接着是n行整数，表示马从A村到B村的所用的分钟数（小于1000）
 *   程序输出：1个整数，表示所有马匹均运到B村的最小总耗时。
 *   例如，
 * 输入：
 * 3
 * 1
 * 2
 * 4
 * 程序应输出：
 * 7
 * 输入：
 * 4
 * 1
 * 4
 * 2
 * 5
 * 程序应该输出：
 * 13
 *
 * @author henengqiang
 * @date 2019/06/11
 */
public class TransferHorsesQ {

    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int output = transferPlan();
        System.out.println("运送这些马匹到目的地最少需要【" + output + "】分钟");
    }

    private static int transferPlan() {

        // 一次运送的马匹数
        int horses = 2;

        System.out.println("输入马匹数：");
        int n = sc.nextInt();

        int[] costs = new int[n];
        for (int i = 0; i < n; i++) {
            System.out.println("输入第" + (i + 1) + "匹马到达目的地需要的时间(分钟)：");
            costs[i] = sc.nextInt();
        }

        int timeNeeded = 0;
        SortUtils.bubbleSort(costs, true);
        int leastTime = costs[0];
        if (n == 1) {
            // 只有一匹马，耗时就为这匹马到达目的地的时间
            timeNeeded = costs[0];
        } else if (n > horses) {
            // 这样就不会出现有多匹最快的马会出现的bug
            // 这段逻辑只能对应一次性只能运送两匹马，若一次性运送超过两匹马需要其他计算方式
            for (int i = 1; i < costs.length; i++) {
                timeNeeded += costs[i];
            }
            timeNeeded += leastTime * (n - horses);
        } else {
            // 可以一次性运完，耗时为这些马儿中最慢那匹马需要的时间
            timeNeeded = SortUtils.selectMaximumValue(costs, false);
        }

        return timeNeeded;
    }

}
