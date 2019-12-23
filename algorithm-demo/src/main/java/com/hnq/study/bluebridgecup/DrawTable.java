package com.hnq.study.bluebridgecup;

import java.util.Scanner;

/**
 * 画表格
 * 在中文Windows环境下，控制台窗口中也可以用特殊符号拼出漂亮的表格来。
 * 比如：
 * ┌─┬─┐
 * │ │ │
 * ├─┼─┤
 * │ │ │
 * └─┴─┘
 * 其实，它是由如下的符号拼接的：
 * 左上 = ┌
 * 上 =  ┬
 * 右上 =  ┐
 * 左 =  ├
 * 中心 =  ┼
 * 右 =  ┤
 * 左下=  └
 * 下 =  ┴
 * 右下 =  ┘
 * 垂直 =  │
 * 水平 =   ─
 * 本题目要求编写一个程序，根据用户输入的行、列数画出相应的表格来。
 * 例如用户输入：
 * 3 2
 * 则程序输出：
 * ┌─┬─┐
 * │ │ │
 * ├─┼─┤
 * │ │ │
 * ├─┼─┤
 * │ │ │
 * └─┴─┘
 *
 * 用户输入：
 * 2 3
 * 则程序输出：
 * ┌─┬─┬─┐
 * │ │ │ │
 * ├─┼─┼─┤
 * │ │ │ │
 * └─┴─┴─┘
 *
 * @author henengqiang
 * @date 2019/06/13
 */
public class DrawTable {

    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        drawTable();
    }

    private static void drawTable() {
        System.out.println("请输入表格行数：");
        int row = sc.nextInt();
        System.out.println("请输入表格列数：");
        int column = sc.nextInt();

        // 第一行
        System.out.print("⎾ ");
        for (int i = 0; i < column - 1; i++) {
            System.out.print("⏉ ");
        }
        System.out.print("⏋ ");

        for (int j = 0; j < row; j++) {
            System.out.println();
            for (int i = 0; i < column + 1; i++) {
                System.out.print("│ ");
            }
            System.out.println();
            if (j < row - 1) {
                System.out.print("├ ");
                for (int i = 0; i < column - 1; i++) {
                    System.out.print("⎼┼⎼");
                }
                System.out.print("┤ ");
            }
        }

        // 最后一行
        System.out.print("⎿ ");
        for (int i = 0; i < column - 1; i++) {
            System.out.print("⏊ ");
        }
        System.out.print("⏌ ");
    }

}



















