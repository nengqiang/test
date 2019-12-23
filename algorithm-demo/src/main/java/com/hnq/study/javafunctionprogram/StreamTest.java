package com.hnq.study.javafunctionprogram;

import com.google.common.collect.Lists;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author henengqiang
 * @date 2018/10/10
 */
@SuppressWarnings("unchecked")
public class StreamTest {

    public static void main(String[] args) {
//         test1();
//         test2();
//         test3();
//         test4();
//         test5();
//         test7();
//        test8();
//        test9();
//        test10();
//        test11();
//        test12();
//        test13();
//        streamCountT();
        personTComparatorT();
    }

    private static void test1() {
        Stream stream = Stream.empty();

        List<String> list = Arrays.asList("a", "b", "c", "d");
        // 获取串行的 Stream 对象
        Stream listStream = list.stream();
        // 获取并行的 Stream 对象
        Stream parallelListStream = list.parallelStream();

        Stream s = Stream.of("test");
        Stream s1 = Stream.of("a", "b", "c", "d");

        /*
         * 随机生成10个Double类型的Stream并将其打印
         */
        Stream.generate(new Supplier<Double>() {
            @Override
            public Double get() {
                return Math.random();
            }
        }).limit(10).forEach(System.out::println);
        // 上述写法简化为
        Stream.generate(Math::random).limit(10).forEach(System.out::println);
    }

    private static void test2() {
        Stream<String> s = Stream.of("test", "t1", "t2", "tree", "aaaa");
        s.map(n -> n.concat(".txt")).forEach(System.out::println);
    }

    private static void test3() {
        Stream<String> s = Stream.of("test", "t1", "t2", "tree", "aaaa");
        s.flatMap(n -> Stream.of(n.split(""))).forEach(System.out::println);
    }

    private static void test4() {
        List<Integer> nums = Stream.of(Arrays.asList(1, 2, 3), Arrays.asList(1, 4, 9), Arrays.asList(1, 8, 27))
                .flatMap(Collection::stream).collect(Collectors.toList());
        System.out.println(nums);

    }

    private static void test5() {
        Action action = System.out::println;
        action.execute("Hello world!");
        test6(System.out::println, "Hello world!");
    }

    private static void test6(Action action, String str) {
        action.execute(str);
    }

    @FunctionalInterface
    interface Action<T> {
        void execute(T t);
    }

    private static void test7() {
        Stream<Integer> stream = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 0);
        stream.map(x -> (x % 2 == 0 ? x : ++x)).forEach(System.out::println);
    }

    private static void test8() {
        final List<BigDecimal> prices = Arrays.asList(
                new BigDecimal(10), new BigDecimal(20), new BigDecimal(30),
                new BigDecimal(17), new BigDecimal(27), new BigDecimal(37),
                new BigDecimal(15), new BigDecimal(25), new BigDecimal(35)
        );
        final BigDecimal totalOfDiscountedPrices = prices.stream()
                .filter(price -> price.compareTo(BigDecimal.valueOf(20)) > 0)
                .map(price -> price.multiply(BigDecimal.valueOf(0.9)))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println("Total of discounted prices: " + totalOfDiscountedPrices);
    }

    private static void test9() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
        list.forEach(System.out::println);
    }

    private static void test10() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
        System.out.println("Print all numbers: ");
        evaluate(list, n -> true);

        System.out.println("Print no numbers: ");
        evaluate(list, n -> false);

        System.out.println("Print even numbers: ");
        evaluate(list, n -> n % 2 == 0);

        System.out.println("Print odd numbers: ");
        evaluate(list, n -> n % 2 == 1);

        System.out.println("Print numbers greater than 5: ");
        evaluate(list, n -> n > 5);
    }

    private static void evaluate(List<Integer> list, Predicate<Integer> predicate) {
        list.forEach(n -> System.out.print(predicate.test(n) ? n + " " : ""));
        System.out.println();
    }

    private static void test11() {
        List<String> list = Arrays.asList("a", "bb", "dddd", "ccc", "eeeee");
        list.stream().filter(e -> e.length() > 3).forEach(System.out::println);
    }

    private static void test12() {
        List<String> list = Arrays.asList("a", "alice", "alibaba", "apple", "apig");
        list.stream().filter(e -> e.length() > 2 && e.contains("a")).forEach(System.out::println);
    }

    private static void test13() {
        List<String> list = Arrays.asList("a", "ccc", "bb", "dddd", "eeeee");
        list.stream().sorted(String::compareTo).limit(3).forEach(System.out::println);
        String max = list.stream().max(Comparator.comparing(String::length)).get();
        String min = list.stream().min(Comparator.comparing(String::length)).get();
        System.out.println("maxLength: " + max + ", minLength: " + min);
    }

    private static void streamCountT() {
        List<String> list = Arrays.asList("abel", "don", "bruce", "sean");
        // 并行计算
        int sum1 = list.stream().parallel().mapToInt(String::length).sum();
        System.out.println(sum1);

        // reduce 求和
        int sum2 = list.stream().parallel().mapToInt(String::length).reduce(0, (x, y) -> (x + y));
        System.out.println("reduce求和： " + sum2);

        // 使用 summaryStatistics 方法获得 stream 中元素的各种汇总数据
        IntSummaryStatistics statistics = list.stream().mapToInt(String::length).summaryStatistics();
        System.out.println("求长度平均数: " + statistics.getAverage());
        System.out.println("长度最小的: " + statistics.getMin());
        System.out.println("长度最大的: " + statistics.getMax());
        System.out.println("求总和: " + statistics.getSum());
        System.out.println("元素个数: " + statistics.getCount());
    }

    private static void personTComparatorT() {
        List<Person> people = Lists.newArrayList();
        for (int i = 0; i < 4; i++) {
            Person person = new Person();
            person.setName("abel-" + i);
            person.setAge(20 + i);
            people.add(person);
        }
        people.sort(Comparator.comparing(Person::getName));
        people.forEach(System.out::println);
    }


}
