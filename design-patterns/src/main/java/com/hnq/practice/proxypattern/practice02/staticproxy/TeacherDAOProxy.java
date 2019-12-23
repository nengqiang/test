package com.hnq.practice.proxypattern.practice02.staticproxy;

/**
 * @author henengqiang
 * @date 2019/12/19
 */
public class TeacherDAOProxy implements ITeacherDAO {

    /**
     * 目标对象，通过接口来聚合
     */
    private ITeacherDAO target;

    public TeacherDAOProxy(ITeacherDAO target) {
        this.target = target;
    }

    @Override
    public void teach() {
        System.out.println("代理开始...");
        target.teach();
        System.out.println("代理结束");
    }

}
