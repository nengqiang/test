package com.hnq.practice.observepattern.practice01.observer;

import com.hnq.practice.observepattern.practice01.subject.BaseListSubject;

/**
 * IListObserver是清单观察者接口。对应于观察者模式的参与者，IListObserver是观察者接口Observer。
 *
 * @author henengqiang
 * @date 2019/07/02
 */
public interface IListObserver {

    /**
     * 清单改变
     */
    void listHasChanged(BaseListSubject baseListSubject);

}
