package com.hnq.study.bean;

/**
 * @author henengqiang
 * @date 2019/04/02
 */
public class StudentA {

    // ------------------构造方法------------------

    /**
     * 默认构造方法
     */
    public StudentA(String str) {
        System.out.println("默认构造方法 s = " + str);
    }

    /**
     * 无参构造方法
     */
    public StudentA() {
        System.out.println("公有、无参构造方法执行了...");
    }

    /**
     * 有一个参数的构造方法
     */
    public StudentA(char name) {
        System.out.println("姓名：" + name);
    }

    /**
     * 多个参数的构造方法
     */
    public StudentA(String name, int age) {
        System.out.println("姓名：" + name + "，年龄：" + age);
    }

    /**
     * 受保护的构造方法
     */
    protected StudentA(boolean n) {
        System.out.println("受保护的构造方法 n = " + n);
    }

    /**
     * 私有构造方法
     */
    private StudentA(int age) {
        System.out.println("私有构造方法 年龄："+ age);
    }
}
