package com.hnq.study.javafunctionprogram;

import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;

/**
 * @author henengqiang
 * @date 2018/10/11
 */
public class CarTest {

    public static void main(String[] args) {
        // 构造器应引用，语法是Class::new，注意构造器没有参数
        final CarTest carTest = CarTest.create(CarTest::new);
        final List<CarTest> carTests = Collections.singletonList(carTest);
        // 静态方法引用，语法是Class::static_method，注意这个方法接受一个Class类型的参数
        carTests.forEach(CarTest::collide);
        // 特定类的任意对象的方法引用，语法是Class::method，注意这个方法没有参数
        carTests.forEach(CarTest::repair);
        // 特定对象的方法引用，语法是instance::method，注意这个方法接受一个Class类型的参数
        final CarTest police = CarTest.create(CarTest::new);
        carTests.forEach(police::follow);
    }

    private static CarTest create(final Supplier<CarTest> supplier) {
        return supplier.get();
    }

    private static void collide(final CarTest carTest) {
        System.out.println("Collide " + carTest.toString());
    }

    private void follow(final CarTest another) {
        System.out.println("Following the " + another.toString());
    }

    private void repair() {
        System.out.println("Repaired " + this.toString());
    }

}
