package com.hnq.study.pta;

import java.util.*;

/**
 * @author henengqiang
 * @date 2019/09/27
 */
public class Q1021 {

    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        logic();
    }

    private static void logic() {
        String n = sc.nextLine();
        Map<Integer, Integer> map = new HashMap<>(n.length() * 4 / 3 + 1);
        for (int i = 0; i < n.length(); i++) {
            int k = Integer.parseInt(n.substring(i, i + 1));
            map.put(k, map.get(k) == null ? 1 : map.get(k) + 1);
        }

        List<Integer> temp = new ArrayList<>(map.keySet());
        temp.sort(Comparator.naturalOrder());

        for (Integer i : temp) {
            System.out.println(i + ":" + map.get(i));
        }
    }

}
