package com.hnq.study.lintcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author henengqiang
 * @date 2019/09/05
 */
public class FizzBuzz {

    public static void main(String[] args) {
        System.out.println(fizzBuzz(10));
        System.out.println(fizzBuzz(15));
    }

    private static List<String> fizzBuzz(int n) {
        List<String> result = new ArrayList<>(n * 4 / 3 + 1);
        for (int i = 1; i <= n; i++) {
            int re3 = i % 3;
            int re5 = i % 5;
            String info = (re3 == 0 ? "fizz" : (re5 == 0 ? "buzz" : String.valueOf(i)));
            result.add(re3 == 0 && re5 == 0 ? "fizz buzz" : info);
        }
        return result;
    }

}
