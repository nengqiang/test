package com.hnq.study.use;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * 获取成员方法并使用：
 *
 * 1.批量的：
 *      public Method[] getMethods(): 获取所有的"公有方法"（包含了父类的方法也包含了Object类）
 *      public Method[] getDeclaredMethods(): 获取所有的成员方法，包括私有的（不包括继承的）
 *
 * 2.获取单个的：
 *      public Method getMethod(String name, Class<?>... parameterTypes):
 *          name: 方法名
 *          class...: 形参的Class类型对象
 *
 *      public Method getDeclaredMethod(String name, Class<?>... parameterTypes)
 *
 * 调用方法：
 *      Method --> public Object invoke(Object obj, Object... args):
 *          obj: 要调用的方法的对象
 *          args: 调用方法时所传递的实参
 *
 * @author henengqiang
 * @date 2019/04/02
 */
public class GetMethods {

    @SuppressWarnings("unchecked")
    public static void main(String[] args)
            throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException,
            InstantiationException {
        // 1.获取Class对象
        Class stuClass = Class.forName("com.hnq.study.bean.StudentC");

        // 2.获取所有公有方法
        System.out.println("---> 获取所有公有方法");
        Method[] methods = stuClass.getMethods();
        Arrays.stream(methods).forEach(System.out::println);

        System.out.println("---> 获取所有方法，包括私有方法");
        methods = stuClass.getDeclaredMethods();
        Arrays.stream(methods).forEach(System.out::println);

        System.out.println("--> 获取公有的show1()方法");
        Method method = stuClass.getMethod("show1", String.class);
        System.out.println(method);
        // 实例化一个StudentC对象
        Object obj = stuClass.getConstructor().newInstance();
        method.invoke(obj, "Alice");

        System.out.println("---> 获取私有的show4()方法");
        method = stuClass.getDeclaredMethod("show4", int.class);
        System.out.println(method);
        // 解除私有限定
        method.setAccessible(true);
        // 需要两个参数，一个是要调用的对象（用反射获取），一个是实参
        Object result = method.invoke(obj, 20);
        System.out.println("返回值："+ result);
    }

}
