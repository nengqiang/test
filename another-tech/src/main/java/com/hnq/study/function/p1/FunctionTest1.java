package com.hnq.study.function.p1;


import org.junit.jupiter.api.Test;

import java.util.function.Function;

/**
 * @author henengqiang
 * @date 2019/11/08
 */
class FunctionTest1 {

    @Test
    void f1() {
        Function<Integer, Integer> f = i -> i + 1;
        System.out.println(f.apply(5));
    }

    @Test
    void f2() {
        Function<Integer, Integer> f1 = i -> i + 1;
        Function<Integer, Integer> f2 = i -> i * i;
        System.out.println(cal(f1, 5));
        System.out.println(cal(f2, 2));
    }

    private int cal(Function<Integer, Integer> f, int input) {
        return f.apply(input);
    }

    @Test
    void f3() {
        Function<Integer, Integer> f1 = i -> i * 2;
        Function<Integer, Integer> f2 = i -> i * i;
        System.out.println(compute1(f1, f2, 5));
        System.out.println(compute2(f1, f2, 5));
    }

    private int compute1(Function<Integer, Integer> after, Function<Integer, Integer> before, int in) {
        return after.compose(before).apply(in);
    }

    private int compute2(Function<Integer, Integer> after, Function<Integer, Integer> before, int in) {
        return before.andThen(after).apply(in);
    }


}
