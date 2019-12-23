package com.hnq.study.practice;

import com.hnq.toolkit.util.SortUtils;

import java.util.Scanner;

/**
 * 题目描述 Description
 * 元旦快到了，校学生会让乐乐负责新年晚会的纪念品发放工作。为使得参加晚会的同学所获得的纪念品价值相对均衡，
 * 他要把购来的纪念品根据价格进行分组，但每组最多只能包括两件纪念品，并且每组纪念品的价格之和不能超过一个给定的整数。
 * 为了保证在尽量短的时间内发完所有纪念品，乐乐希望分组的数目最少。
 *
 * 你的任务是写一个程序，找出所有分组方案中分组数最少的一种，输出最少的分组数目。
 *
 * 输入描述 Input Description
 * 包含n+2行：
 *
 * 第1行包括一个整数w，为每组纪念品价格之和的上限。
 *
 * 第2行为一个整数n，表示购来的纪念品的总件数。
 *
 * 第3~n+2行每行包含一个正整数pi (5 <= pi <= w)，表示所对应纪念品的价格。
 *
 * 输出描述 Output Description
 * 仅一行，包含一个整数，即最少的分组数目。
 *
 * @author henengqiang
 * @date 2019/07/02
 */
public class SouvenirGrouping {

    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        groupingLogic();
    }

    private static void groupingLogic() {
        System.out.println("请输入每组纪念品价格上限：");
        int maxPrice = sc.nextInt();

        System.out.println("请输入纪念品总件数：");
        int num = sc.nextInt();
        sc.nextLine();

        System.out.println("请输入纪念品价格范围，用空格隔开");
        String[] priceRange = sc.nextLine().split("\\s");
        int price0 = Integer.valueOf(priceRange[0]);
        int price1 = Integer.valueOf(priceRange[1]);

        int[] prices = SortUtils.generateArrayBetween(num, price1, price0);
        SortUtils.quickSort(prices, false);
        int groupingNum = 0;
        // TODO: 2019-07-02 henengqiang
        for (int i = 0; i < num / 2 + 1; i++) {
            for (int j = num - 1; j > i ; j--) {
                if (prices[i] + prices[j] > maxPrice) {
                    groupingNum += 1;
                } else {

                }
            }
        }

    }

}
