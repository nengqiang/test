package com.hnq.study.bean;

/**
 * @author henengqiang
 * @date 2019/04/02
 */
public class StudentB {

    public StudentB() {
    }

    // ----- 字段 ----- //

    public String name;
    protected int age;
    char sex;
    private String phoneNum;

    @Override
    public String toString() {
        return "StudentB{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", sex=" + sex +
                ", phoneNum='" + phoneNum + '\'' +
                '}';
    }
}
