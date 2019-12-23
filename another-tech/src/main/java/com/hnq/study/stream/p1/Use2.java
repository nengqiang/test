package com.hnq.study.stream.p1;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author henengqiang
 * @date 2019/11/08
 */
public class Use2 {

    private static List<String> names = Lists.newArrayList("alice", "bob", "c", "tu", "lalal", "b");

    public static void main(String[] args) {
        String res = names.stream()
                .filter(s -> s.length() > 1)
                .map(Use2::capitalizeString)
                .collect(Collectors.joining(","));
        System.out.println(res);

    }

    private static String capitalizeString (String s) {
        return s.substring(0, 1).toUpperCase() + s.substring(1);
    }

}
