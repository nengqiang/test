package com.hnq.study.practice;

import com.google.common.collect.Maps;
import com.hnq.toolkit.util.SortUtils;

import java.util.Map;
import java.util.Scanner;

/**
 * 小白鼠排队
 *
 * 题目描述：
 * N只小白鼠(1 <= N <= 100)，每只鼠头上戴着一顶有颜色的帽子。现在称出每只白鼠的重量，
 * 要求按照白鼠重量从大到小的顺序输出它们头上帽子的颜色。帽子的颜色用“red”，“blue”等字符串来表示。
 * 不同的小白鼠可以戴相同颜色的帽子。白鼠的重量用整数表示。
 *
 * 输入：
 * 多案例输入，每个案例的输入第一行为一个整数N，表示小白鼠的数目。
 * 下面有N行，每行是一只白鼠的信息。第一个为不大于100的正整数，表示白鼠的重量；
 * 第二个为字符串，表示白鼠的帽子颜色，字符串长度不超过10个字符。
 * 注意：白鼠的重量各不相同。
 * 输出：
 * 每个案例按照白鼠的重量从大到小的顺序输出白鼠的帽子颜色。
 *
 *
 * 样例输入：
 * 3
 * 30 red
 * 50 blue
 * 40 green
 *
 *
 * 样例输出：
 * blue
 * green
 * red
 *
 * @author henengqiang
 * @date 2019/06/17
 */
public class WhiteMouseSort {

    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        whiteMouseSortLogic();
    }

    private static void whiteMouseSortLogic() {
        System.out.println("请输入小白鼠只数：");
        int x = sc.nextInt();

        /*
         * nextLine() 方法不能放在 nextInt( 代码段的后面，其实，他不是跳过了，而是他已经有内容了，内容就是 \n。
         * 因为 nextInt() 接收一个整型字符，不会读取 \n，nextLine() 读入一行文本，会读入 \n 字符。
         *【解决办法】：可以在 nextInt() 和 nextLine() 中间加一个 nextLine() 语句来接收这个 \n 。
         */
        sc.nextLine();

        Map<Integer, String> weightHatMap = Maps.newHashMap();
        for (int i = 0; i < x; i++) {
            System.out.println("请输入第" + (i + 1) + "只小白鼠体重和它帽子颜色，用空格隔开：");
            String[] info = sc.nextLine().split("\\s");
            weightHatMap.put(Integer.valueOf(info[0]), info[1]);
        }

        weightHatMap = SortUtils.sortMapByKey(weightHatMap, true);
        weightHatMap.values().forEach(System.out::println);
    }

}
