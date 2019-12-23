package com.hnq.study.function.p1;

import com.google.common.collect.Lists;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * 级联 lambda 表达式
 *
 * @author henengqiang
 * @date 2019/11/22
 */
class FunctionTest2 {

    @Test
    void cascadeTest() {
        List<Integer> list = Lists.newArrayList(10, 20, 30, 40, 50, 60, 70, 80, 90);
        System.out.println(list);

        List<Integer> valuesOver25 = list.stream().filter(isGreaterThan.apply(25)).collect(Collectors.toList());
        System.out.println(valuesOver25);

        List<Integer> valuesOver55 = list.stream().filter(isGreaterThan.apply(55)).collect(Collectors.toList());
        System.out.println(valuesOver55);
    }

    private static Function<Integer, Predicate<Integer>> isGreaterThan = pivot -> candidate -> candidate > pivot;

}
