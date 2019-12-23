package com.hnq.study.bluebridgecup;

import java.util.Scanner;

/**
 * 1的个数
 * 从1到20的所有数字中含有多少个“1”
 * 仔细数一下，应该是12个。
 * 那么从1到1000的整数中，含有多少个“1”呢？
 *
 * @author henengqiang
 * @date 2019/06/14
 */
public class HowManyOneInNumbers {

    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        calculateTheNumsOfOne();
    }

    private static void calculateTheNumsOfOne() {
        System.out.println("输入范围（右边界）：");
        int bound = sc.nextInt();
        int count = 0;
        for (int i = 1; i <= bound; i++) {
            int temp = count;
            count = isOne(i, count);
            if (count > temp) {
                System.out.println(i);
            }
        }
        System.out.printf("1 ~ %d 之间有【%d】个 1", bound, count);
    }

    private static int isOne(int num, int count) {
        String str = String.valueOf(num);
        if (str.length() == 1) {
            if (num == 1) {
                count++;
            }
        } else {
            if (num % 10 == 1) {
                count++;
            }
            count = isOne(num / 10, count);
        }
        return count;
    }

}















