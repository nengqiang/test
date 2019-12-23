package com.hnq.study.javafunctionprogram;

import java.util.function.Function;

/**
 * @author henengqiang
 * @date 2018/10/10
 */
public class FunctionTest {

    public static void main(String[] args) {
        Function<Integer, Integer> f = s -> s++;
        Function<Integer, Integer> g = s -> s * 2;

        /*
         * 下面表示再执行f时，先执行g，并且执行f时使用g的输出当作输入
         * 相当于以下代码：
         * Integer a = g.apply(1);
         * System.out.println(f.apply(a));
         */
        System.out.println(f.compose(g).apply(1));

        /*
         * 下面表示执行f的apply后使用其输出当作输入再执行g的apply
         * 相当于以下代码：
         * Integer a = f.apply(1);
         * System.out.println(g.apply(a));
         */
        System.out.println(f.andThen(g).apply(1));

        /*
         * identity方法会返回一个不进行任何处理的Function，即输出与输入值相等
         */
        System.out.println(Function.identity().apply("a"));

    }

}
