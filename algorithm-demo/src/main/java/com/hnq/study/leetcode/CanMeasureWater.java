package com.hnq.study.leetcode;

/**
 * 有两个容量分别为 x升 和 y升 的水壶以及无限多的水。请判断能否通过使用这两个水壶，从而可以得到恰好 z升 的水？
 * 如果可以，最后请用以上水壶中的一或两个来盛放取得的 z升 水。
 *
 * 你允许：
 *
 *   装满任意一个水壶
 *   清空任意一个水壶
 *   从一个水壶向另外一个水壶倒水，直到装满或者倒空
 *
 * 示例：
 *  输入: x = 3, y = 5, z = 4
 * 输出: True
 *  输入: x = 2, y = 6, z = 5
 * 输出: False
 *
 * 找到x, y的最大公约数能否z被整除
 *
 * @author henengqiang
 * @date 2020/3/21
 */
public class CanMeasureWater {

    public static void main(String[] args) {
        System.out.println(canMeasureWater(3, 5, 4));
        System.out.println(canMeasureWater(2, 6, 5));
    }

    private static boolean canMeasureWater(int x, int y, int z) {
        if (z == 0 || z == x || z == y || z == x + y) {
            return true;
        }
        if (z > x + y) {
            return false;
        }
        int min = Math.min(x, y);
        int max = Math.max(x, y);
        int commonDivisor = 0;
        for (int i = min; i > 0; i--) {
            if (max % i == 0 && min % i == 0) {
                commonDivisor = i;
                break;
            }
        }
        if (commonDivisor == 0) {
            return false;
        }
        return z % commonDivisor == 0;
    }

}
