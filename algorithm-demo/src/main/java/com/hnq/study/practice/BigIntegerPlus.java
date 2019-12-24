package com.hnq.study.practice;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author henengqiang
 * @date 2019/12/24
 */
public class BigIntegerPlus {

    public static void main(String[] args) {
        try {
            System.out.println(factorial(5));
            System.out.println(factorial(10));
            System.out.println(factorial(100));
            System.out.println(factorial(1000));
            System.out.println(factorial(10000));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String factorial(int n) throws Exception {
        if (n < 0) {
            throw new IllegalAccessException("input integer must greater than zero");
        }
        int[] arr = new int[2 * n];
        arr[arr.length - 1] = 1;
        IntStream.rangeClosed(1, n).forEach(i -> intPlusArray(arr, i));

        int flag = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] == 0 && arr[i + 1] != 0) {
                flag = i + 1;
                break;
            }
        }
        return IntStream.range(flag, arr.length).mapToObj(i -> String.valueOf(arr[i])).collect(Collectors.joining());
    }

    private static void intPlusArray(int[] arr, int x) {
        IntStream.range(0, arr.length).forEach(i -> arr[i] *= x);
        for (int i = arr.length - 1; i > 0; i--) {
            arr[i - 1] += arr[i] / 10;
            arr[i] %= 10;
        }
    }

}
