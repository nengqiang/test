package com.hnq.study.lintcode;
import com.google.common.base.Stopwatch;

import	java.util.ArrayList;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author henengqiang
 * @date 2019/09/18
 */
public class DigitCounts {

    public static void main(String[] args) {
        Stopwatch watch = Stopwatch.createStarted();
        System.out.println(digitCounts(1, 12));
        System.out.println(watch.toString());

        watch.reset();
        watch.start();
        System.out.println(digitCounts2(1, 12));
        watch.stop();
        System.out.println(watch.toString());
    }

    private static int digitCounts(int k, int n) {
        List<String> var = new ArrayList<> ();
        for (int i = 0; i <= n; i++) {
            List<String> temp = Arrays.stream(String.valueOf(i).split("")).collect(Collectors.toList());
            var.addAll(temp);
        }
        return (int) var.stream().mapToInt(Integer::parseInt).filter(x -> x == k).count();
    }

    private static int digitCounts2(int k, int n) {
        if (k == 0 && n == 0) {
            return 1;
        }
        int count = k == 0 ? 1 : 0;
        for (int i = 1; i <= n; i++) {
            int x = i;
            while (x != 0) {
                if (x % 10 == k) {
                    count++;
                }
                x /= 10;
            }
        }
        return count;
    }

}
