package com.hnq.practice.proxypattern.practice02.dynamicproxy;

/**
 * @author henengqiang
 * @date 2019/12/19
 */
public class TeacherDAO implements ITeacherDAO {

    @Override
    public void teach() {
        System.out.println("👩‍🏫授课🀄️");
    }

}
