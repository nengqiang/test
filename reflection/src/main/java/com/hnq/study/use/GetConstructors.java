package com.hnq.study.use;

import com.hnq.study.bean.StudentA;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

/**
 * 通过Class对象可以获取到某个类中的：构造方法、成员变量、成员方法；并访问成员
 *
 * 1. 获取构造方法
 *      1）批量的方法：
 *          public Constructor[] getConstructors(): 所有的"公有的"构造方法
 *          public Constructor[] getDeclaredConstructors(): 获取所有的构造方法（包括私有、受保护、默认和公有）
 *
 *      2）获取单个的方法，并调用：
 *          public Constructor getConstructor(Class... parameterTypes): 获取单个的"公有的"构造方法
 *          public Constructor getDeclaredConstructor(Class... parameterTypes): 获取"某个构造方法"，可以是私有的
 *
 *      调用构造方法：
 *      Constructor --> newInstance(Object... initargs)
 *
 * @author henengqiang
 * @date 2019/04/02
 */
public class GetConstructors {

    @SuppressWarnings("unchecked")
    public static void main(String[] args)
            throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException,
            InstantiationException {
        // 1.加载Class对象
        Class clazz = Class.forName("com.hnq.study.bean.StudentA");

        // 2.获取构造方法
        System.out.println("---> 所有公有构造方法");
        Constructor[] constructors = clazz.getConstructors();
        Arrays.stream(constructors).forEach(System.out::println);

        System.out.println("---> 获取所有构造方法（包括私有、受保护、默认和公有）");
        constructors = clazz.getDeclaredConstructors();
        Arrays.stream(constructors).forEach(System.out::println);

        System.out.println("---> 获取公有、无参的构造方法");
        Constructor constructor = clazz.getConstructor();
        System.out.println(constructor);
        // 调用构造方法
        Object obj = constructor.newInstance();
        System.out.println(obj);

        System.out.println("---> 获取私有构造方法，并使用");
        constructor = clazz.getDeclaredConstructor(int.class);
        System.out.println(constructor);
        // 调用构造方法
        // 暴力访问（忽略掉访问修饰符）
        constructor.setAccessible(true);
        obj = constructor.newInstance(25);
        System.out.println(obj);
    }

}
