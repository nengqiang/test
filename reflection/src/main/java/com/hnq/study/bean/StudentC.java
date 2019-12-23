package com.hnq.study.bean;

/**
 * @author henengqiang
 * @date 2019/04/02
 */
public class StudentC {

    // ----- 成员方法 ----- //

    public void show1(String s) {
        System.out.println("执行公有成员方法 s = " + s);
    }

    protected void show2() {
        System.out.println("执行受保护的无参成员方法...");
    }

    void show3() {
        System.out.println("执行默认的无参成员方法");
    }

    private String show4(int age) {
        System.out.println("执行私有成员方法 age = " + age);
        return "show4";
    }

}
