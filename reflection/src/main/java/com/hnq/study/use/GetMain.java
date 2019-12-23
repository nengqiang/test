package com.hnq.study.use;

import java.lang.reflect.Method;

/**
 * @author henengqiang
 * @date 2019/04/03
 */
public class GetMain {

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        try {
            // 1. 获取StudentD对象的字节码
            Class clazz = Class.forName("com.hnq.study.bean.StudentD");

            // 2. 获取main方法
            Method main = clazz.getMethod("main", String[].class);
            // 3. 调用main方法
            main.invoke(null, (Object) new String[] {"a", "b", "c"});
            main.invoke(null, new Object[] {new String[] {"a", "b", "c"}});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
