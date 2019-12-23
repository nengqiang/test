package com.hnq.study.use;

import com.hnq.study.bean.StudentA;

/**
 * 获取class对象的三种方式
 * 1 Object --> getClass();
 * 2 任何数据类型（包括基本数据类型）都有一个"静态"的class属性
 * 3 通过Class类的静态方法：forName(String className)（常用）
 *
 * @author henengqiang
 * @date 2019/04/02
 */
public class GetClass {

    public static void main(String[] args) {
        System.out.println(getClass1());
        System.out.println(getClass1() == getClass2());
        System.out.println(getClass1() == getClass3());
    }

    private static Class getClass1() {
        // new 产生一个class对象
        StudentA studentA = new StudentA();
        // 获取class对象
        return studentA.getClass();
    }

    private static Class getClass2() {
        return StudentA.class;
    }

    private static Class getClass3() {
        try {
            // 注意此字符串必须是真实路径，就是带包名的路径：包名.类名
            return Class.forName("com.hnq.study.bean.StudentA");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

}
