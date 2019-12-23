package com.hnq.study.pta;

import java.util.Scanner;

/**
 * @author henengqiang
 * @date 2019/11/29
 */
public class Q1026 {

    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        String in = sc.nextLine();
        String[] clc = in.split("\\s");
        long duration = Long.parseLong(clc[1]) - Long.parseLong(clc[0]);

        long hours = duration / 100 / 60 / 60;
        long minutes = duration % (100 * 60 * 60) / 100 / 60;
        double seconds = duration % (100 * 60 * 60) % (100 * 60) / 100.0;

        String h = hours > 9 ? String.valueOf(hours) : "0" + hours;
        String m = minutes > 9 ? String.valueOf(minutes) : "0" + minutes;
        String st = String.format("%.0f", seconds);
        String s = st.length() > 1 ? st : "0" + st;

        System.out.println(h + ":" + m + ":" + s);
    }

}
