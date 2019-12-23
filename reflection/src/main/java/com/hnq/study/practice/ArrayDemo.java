package com.hnq.study.practice;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * @author henengqiang
 * @date 2019/08/08
 */
public class ArrayDemo {

    public static void main(String[] args) {
        defineArrayViaReflection();
    }

    private static void defineArrayViaReflection() {
        int len = 10;
        Class c = int.class;
        Object o = Array.newInstance(c, len);
        IntStream.range(0, len).forEach(i -> Array.set(o, i, i));
        int[] arr = (int[]) o;
        System.out.println(Arrays.toString(arr));
    }

}
