package com.hnq.study.tenquestions;

import java.util.Scanner;

/**
 * 画菱形
 *
 * @author henengqiang
 * @date 2018-12-11
 */
public class DrawDiamond {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter layers: ");
        int layer = scan.nextInt();
        drawDiamond(layer);
    }

    private static void drawDiamond(int layer) {
        for (int i = 0; i <= layer; i++) {
            for (int j = i; j <= layer - 1; j++) {
                System.out.print(" ");
            }
            for (int j = 0; j < 2 * i - 1; j++) {
                System.out.print("*");
            }
            System.out.println();
        }

        for (int i = 0; i <= layer; i++) {
            for (int j = i; j >= 0; j--) {
                System.out.print(" ");
            }
            for (int j = 2 * (layer - 2); j > 2 * i - 1; j--) {
                System.out.print("*");
            }
            System.out.println();
        }
    }

}
