package com.hnq.study.pta;

import java.util.Scanner;

/**
 * @author henengqiang
 * @date 2019/11/29
 */
public class Q1027 {

    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        String in = sc.nextLine();
        String[] temp = in.split("\\s");
        int x = Integer.parseInt(temp[0]);
        String f = temp[1];

        int layers = 0;
        int n = 1;
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            layers++;
            n += 2 * (2 * layers + 1);
            if (n == x) {
                break;
            }
            if (n > x) {
                layers--;
                n -= 2 * (2 * layers + 1);
                break;
            }
        }

        for (int i = layers; i >= 0; i--) {
            for (int j = 0; j < layers - i; j++) {
                System.out.print(" ");
            }
            for (int j = 0; j < 2 * i + 1; j++) {
                System.out.print(f);
            }
            System.out.println();
        }

        for (int i = 1; i <= layers; i++) {
            for (int j = 0; j < layers - i; j++) {
                System.out.print(" ");
            }
            for (int j = 0; j < 2 * i + 1; j++) {
                System.out.print(f);
            }
            System.out.println();
        }
        System.out.println(Math.abs(x - n));
    }

}
