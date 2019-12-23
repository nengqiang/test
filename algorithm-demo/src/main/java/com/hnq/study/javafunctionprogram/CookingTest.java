package com.hnq.study.javafunctionprogram;

import java.util.function.Consumer;

/**
 * @author henengqiang
 * @date 2018/10/11
 */
public class CookingTest {

    public static void main(String[] args) {
        cook();
    }

    private static void cook() {
        CookingTest cookingTest = new CookingTest();
        cookingTest.doTask("蔬菜", material -> System.out.println("清洗" + material));
        cookingTest.doTask("蔬菜", material -> System.out.println(material + "切片"));
        cookingTest.doTask("食用油", material -> System.out.println(material + "烧热"));
        cookingTest.doTask("", material -> System.out.println("炒菜"));
    }

    private void doTask(String material, Consumer<String> consumer) {
        consumer.accept(material);
    }

}
