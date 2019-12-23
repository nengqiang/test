package com.hnq.study.use;

import com.google.common.collect.Lists;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * 通过反射越过泛型检查
 *
 * 例如：有一个String泛型的集合，怎样能向这个集合中添加Integer类型的值
 *
 * @author henengqiang
 * @date 2019/04/04
 */
public class OverridingGenericChecks {

    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        service();
    }

    @SuppressWarnings("unchecked")
    private static void service() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        List<String> strings = Lists.newArrayList();
        strings.add("aaa");
        strings.add("bbb");

        // 获取list的Class对象，反向调用add()方法，添加数据
        Class listClass = strings.getClass();
        Method method = listClass.getMethod("add", Object.class);
        method.invoke(strings, 100);

        strings.forEach(System.out::println);
    }

}
