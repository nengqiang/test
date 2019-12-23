package com.hnq.study.practice;

/**
 * 汉诺塔
 *
 * @author henengqiang
 * @date 2019/12/13
 */
public class HanoiTower {

    public static void main(String[] args) {
        move(3, 'A', 'B', 'C');
    }

    private static void move(int n, char a, char b, char c) {
        if (n == 1) {
            // 将A柱最底层的圆盘移动到C柱
            System.out.println(a + " --> " + c);
        } else {
            // 借助C柱，将n-1个圆盘从A柱移动到B柱
            move(n - 1, a, c, b);
            // 将A柱最底层的圆盘移动到C柱
            System.out.println(a + " --> " + c);
            // 借助A柱，将n-1个圆盘从B柱移动到C柱
            move(n - 1, b, a, c);
        }
    }

}
