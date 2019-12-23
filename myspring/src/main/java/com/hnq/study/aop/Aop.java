package com.hnq.study.aop;

/**
 * @author henengqiang
 * @date 2019/12/05
 */
public interface Aop<T> {

    void pro();

    T after(T obj);

}
