package com.hnq.study.use;

import com.hnq.study.bean.StudentB;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

/**
 * 获取成员变量并使用：
 *
 * 1.批量的
 *      1）Field[] getFields(): 获取所有的"公有字段"
 *      2）Field[] getDeclaredFields(): 获取所有字段，包括：私有、受保护、默认和公有
 *
 * 2.获取单个的
 *      1）public Field getField(String fieldName): 获取某个"公有的"字段
 *      2）public Field getDeclaredField(String fieldName): 获取某个字段（可以是私有的）
 *
 *  设置字段的值：
 *      Field --> public void set(Object obj, Object value):
 *          obj: 要设置的字段所在的对象
 *          value: 要为字段设置的值
 *
 * @author henengqiang
 * @date 2019/04/02
 */
public class GetFields {

    @SuppressWarnings("unchecked")
    public static void main(String[] args)
            throws ClassNotFoundException, NoSuchFieldException, NoSuchMethodException, IllegalAccessException,
            InvocationTargetException, InstantiationException {
        // 1. 获取Class对象
        Class stuClass = Class.forName("com.hnq.study.bean.StudentB");

        // 2. 获取字段
        System.out.println("--> 获取所有公有字段");
        Field[] fields = stuClass.getFields();
        Arrays.stream(fields).forEach(System.out::println);

        System.out.println("--> 获取所有字段（包括私有、受保护、默认和公有的）");
        fields = stuClass.getDeclaredFields();
        Arrays.stream(fields).forEach(System.out::println);

        System.out.println("--> 获取公有字段并调用");
        Field field = stuClass.getField("name");
        System.out.println(field);
        // 获取一个对象
        Object obj = stuClass.getConstructor().newInstance();
        // 为字段设置值
        field.set(obj, "Alice");
        StudentB stu = (StudentB) obj;
        System.out.println("验证姓名：" + stu.name);

        System.out.println("---> 获取私有字段并使用");
        field = stuClass.getDeclaredField("phoneNum");
        System.out.println(field);
        // 暴力访问，忽略访问修饰符
        field.setAccessible(true);
        field.set(obj, "15734898788");
        System.out.println("验证电话：" + stu);
    }

}
