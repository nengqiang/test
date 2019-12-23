package com.hnq.study.pta;

import java.util.Scanner;

/**
 * @author henengqiang
 * @date 2019/10/31
 */
public class Q1023 {

    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        logic();
    }

    private static void logic() {
        String in = sc.nextLine();

        int[] ins = new int[10];
        int count = 0;
        String[] split = in.split("\\s");
        for (int i = 0; i < split.length; i++) {
            String s = split[i];
            int t = Integer.parseInt(s);
            count += t;
            ins[i] = t;
        }

        int[] arr = new int[count];

        int k = 0;
        for (int i = 0; i < ins.length; i++) {
            for (int j = 0; j < ins[i]; j++) {
                arr[k++] = i;
            }
        }

        selectSort(arr);

        if (arr[0] == 0) {
            for (int i = 1; i < arr.length; i++) {
                if (arr[i] > 0) {
                    arr[0] = arr[i];
                    arr[i] = 0;
                    break;
                }
            }
        }

        StringBuilder res = new StringBuilder();
        for (int x : arr) {
            res.append(x);
        }
        System.out.println(res);
    }

    private static void selectSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[minIdx] > arr[j]) {
                    minIdx = j;
                }
            }
            if (minIdx != i) {
                int temp = arr[i];
                arr[i] = arr[minIdx];
                arr[minIdx] = temp;
            }
        }
    }
}
