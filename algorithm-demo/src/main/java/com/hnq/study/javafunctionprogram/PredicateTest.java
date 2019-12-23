package com.hnq.study.javafunctionprogram;

import org.junit.jupiter.api.Assertions;

import java.util.function.Predicate;

/**
 * @author henengqiang
 * @date 2018/10/10
 */
public class PredicateTest {

    public static void main(String[] args) {
        Predicate<String> p = "test"::equals;
        Predicate<String> g = o -> o.startsWith("t");

        /*
         * negate: 用于对原来的 Predicate 做取反处理
         * 如当调用 p.test("test") 为 true，则调用 p.negate().test("test") 为 false
         */
        Assertions.assertFalse(p.negate().test("test"));

        /*
         * and: 针对同一输入值，多个 Predicate 均返回 true 时返回 true，否则返回 false
         */
        Assertions.assertTrue(p.and(g).test("test"));

        /*
         * or: 针对同一输入值，多个 Predicate 只要有一个返回 true 则返回 true，否则返回 false
         */
        Assertions.assertTrue(p.or(g).test("ta"));
    }

}
