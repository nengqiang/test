package com.hnq.study.stream.p2;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

/**
 * @author henengqiang
 * @date 2019/11/12
 */
class ReplaceOfTraditionalFor {

    @Test
    void range() {
        for (int i = 0; i < 4; i++) {
            System.out.print(i + " ");
        }

        System.out.println();

        // replace of for
        IntStream.range(0, 4).forEach(i -> System.out.print(i + " "));
        System.out.println();
        IntStream.rangeClosed(0, 4).forEach(i -> System.out.print(i + " "));
    }

    @Test
    void rangeVariable() {
        ExecutorService service = Executors.newFixedThreadPool(10);
        IntStream.range(0, 10).forEach(i -> service.submit(() -> System.out.println("Running task " + i)));
    }

    @Test
    void iterator() {
        int sum = IntStream.iterate(1, e -> e + 3).limit(34).sum();
        System.out.println(sum);
    }

}
