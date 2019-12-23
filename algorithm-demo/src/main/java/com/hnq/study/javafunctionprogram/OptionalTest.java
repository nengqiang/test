package com.hnq.study.javafunctionprogram;


import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @author henengqiang
 * @date 2018/10/11
 */
public class OptionalTest {

    public static void main(String[] args) throws Exception {
//        test1();
//        test2();
//        test3();
//        test4();
//        test5();
        test6();
    }

    private static void test1() {
        Optional o;
        o = Optional.of("test");
        System.out.println(o);
    }

    private static void test2() {
        String good = null;
        Optional o = Optional.ofNullable(good);
        System.out.println(o);
    }

    private static void test3() {
        Optional<String> s = Optional.ofNullable(process());
        s.ifPresent(System.out::println);
    }

    private static String process() {
        return null;
    }

    private static void test4() {
        Optional<String> o = Optional.ofNullable(process());
        System.out.println(o.orElse("test4"));
    }

    private static void test5() throws Exception {
        Optional<String> o = Optional.ofNullable(process());
        System.out.println(o.orElseThrow(() -> new Exception("test5")));
    }

    private static void test6() {
        OptionalTest optionalTest1 = null;
        OptionalTest optionalTest2 = new OptionalTest();
        List<OptionalTest> optionalTests = Arrays.asList(optionalTest1, optionalTest2);
        optionalTests.forEach(optionalTest -> {
            boolean isOptionalTestNull = Optional.ofNullable(optionalTest).isPresent();
            System.out.println("Full name is set ? " + isOptionalTestNull);
        });
    }

}
