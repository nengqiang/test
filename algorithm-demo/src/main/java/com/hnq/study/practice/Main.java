package com.hnq.study.practice;

import java.util.Scanner;

/**
 * @author henengqiang
 * @date 2020/3/18
 */
public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int min = sc.nextInt();
        sc.nextLine();
        int max = sc.nextInt();

        int maxRes = 0;

        for (int i = min; i <= max; i++) {
            for (int j = i; j <= max; j++) {
                if (maxRes < (i ^ j)) {
                    maxRes = i ^ j;
                }
            }
        }
        System.out.println(maxRes);
    }

}
