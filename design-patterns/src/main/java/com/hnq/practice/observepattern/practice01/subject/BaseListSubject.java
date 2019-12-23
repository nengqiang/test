package com.hnq.practice.observepattern.practice01.subject;

import com.google.common.collect.Lists;
import com.hnq.practice.observepattern.practice01.observer.IListObserver;

import java.util.List;

/**
 * BaseListSubject是清单目标抽象类。对应于观察者模式的参与者，BaseListSubject是目标接口Subject。
 *
 * 清单目标抽象类
 *
 * @author henengqiang
 * @date 2019/07/02
 */
public abstract class BaseListSubject {

    private List<IListObserver> listObservers = Lists.newArrayList();

    /**
     * 绑定观察者
     */
    public void attach(IListObserver listObserver) {
        listObservers.add(listObserver);
    }

    /**
     * 解绑观察者
     */
    public void detach(IListObserver listObserver) {
        listObservers.remove(listObserver);
    }

    protected void notifyObservers() {
        listObservers.forEach(listObserver -> listObserver.listHasChanged(this));
    }

}
