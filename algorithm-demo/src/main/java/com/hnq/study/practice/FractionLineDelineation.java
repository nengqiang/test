package com.hnq.study.practice;

import com.google.common.collect.Maps;
import com.hnq.toolkit.util.SortUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;
import java.util.Scanner;

/**
 * 分数线划定
 *
 * 世博会志愿者的选拔工作正在 A 市如火如荼的进行。为了选拔最合适的人才，A 市对所有报名的选手进行了笔试，笔试分数达到面试分数线的
 *
 * 选手方可进入面试。面试分数线根据计划录取人数的150%划定，即如果计划录取m名志愿者，则面试分数线为排名第m150%（向下取整）名的选
 * 手的分数，而最终进入面试的选手为笔试成绩不低于面试分数线的所有选手。
 * 现在就请你编写程序划定面试分数线，并输出所有进入面试的选手的报名号和笔试成绩。
 *
 * [m150% = 3150% = 4.5，向下取整后为4。保证4 个人进入面试的分数线为88，但因为88有重分，所以所有成绩大于等于88 的选手都可以进入面
 * 试，故最终有5个人进入面试。]
 *
 * 输入描述 Input Description
 * 第一行，两个整数n，m（5 ≤ n ≤ 5000，3 ≤ m ≤ n），中间用一个空格隔开，其中n 表示报名参加笔试的选手总数，m 表示计划录取的志
 * 愿者人数。输入数据保证m150%向下取整后小于等于n。
 * 第二行到第 n+1 行，每行包括两个整数，中间用一个空格隔开，分别是选手的报名号k（1000 ≤ k ≤ 9999）和该选手的笔试成绩s（1 ≤ s
 * ≤ 100）。数据保证选手的报名号各不相同。
 *
 * 输出描述 Output Description
 * 第一行，有两个整数，用一个空格隔开，第一个整数表示面试分数线；第二个整数为进入面试的选手的实际人数。
 * 从第二行开始，每行包含两个整数，中间用一个空格隔开，分别表示进入面试的选手的报名号和笔试成绩，按照笔试成绩从高到低输出，如果
 * 成绩相同，则按报名号由小到大的顺序输出。
 *
 * @author henengqiang
 * @date 2019/06/25
 */
public class FractionLineDelineation {

    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        logic();
    }

    private static void logic() {
        System.out.println("请依次输入参加笔试的选手人数，录取的志愿者人数，选手笔试分数可能最小值，选手笔试分数可能最大值，用空格隔开：");
        String input = sc.nextLine();
        if (StringUtils.isNotBlank(input)) {
            String[] inputs = input.trim().split("\\s");
            int participants = Integer.valueOf(inputs[0]);
            int admits = Integer.valueOf(inputs[1]);
            int minWrittenScore = Integer.valueOf(inputs[2]);
            int maxWrittenScore = Integer.valueOf(inputs[3]);

            int[] scores = SortUtils.generateArrayBetween(participants, minWrittenScore, maxWrittenScore);
            Map<Integer, Integer> serialToScore = Maps.newLinkedHashMap();
            for (int i = 0; i < scores.length; i++) {
                serialToScore.put(i + 1, scores[i]);
            }

            SortUtils.quickSort(scores, false);
            serialToScore = SortUtils.sortMapByValue(serialToScore, false);

            int interviewPerson = (int) Math.floor(admits * 150 / 100);
            int interviewScore = scores[interviewPerson - 1];

            int interviewPersonsActual = 0;
            for (int score : scores) {
                if (score >= interviewScore) {
                    interviewPersonsActual++;
                } else {
                    // 因为这个数组是有序数组
                    break;
                }
            }

            System.out.println("面试分数线：" + interviewScore + "，进入面试人数：" + interviewPersonsActual);
            System.out.println("笔试成绩排名：");
            serialToScore.forEach((k, v) -> System.out.println("报名号：" + k + "，笔试成绩：" + v));
        }
    }

}
