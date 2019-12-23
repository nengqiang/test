package com.hnq.practice.observepattern.practice01.concreteobserver;

import com.google.common.collect.Lists;
import com.hnq.practice.observepattern.practice01.concretesubject.DefectedGoodsList;
import com.hnq.practice.observepattern.practice01.observer.IListObserver;
import com.hnq.practice.observepattern.practice01.subject.BaseListSubject;

import java.util.List;

/**
 * GoodsMgt是商品管理类，实现了对存在质量问题的产品的召回。对应于观察者模式的参与者，GoodsMgt是具体观察者ConcreteObserver。
 *
 * @author henengqiang
 * @date 2019/07/02
 */
public class GoodsMgt implements IListObserver {

    /**
     * 已召回商品集合
     */
    private List<String> recalledGoodsList = Lists.newArrayList();

    @Override
    public void listHasChanged(BaseListSubject baseListSubject) {
        // 仅处理问题产品清单的改变
        if (baseListSubject instanceof DefectedGoodsList) {
            recall(((DefectedGoodsList) baseListSubject).getGoodsList());
        }
    }

    /**
     * 召回商品
     */
    public void recall(List<String> goodsList) {
        for (String goods : goodsList) {
            if (!recalledGoodsList.contains(goods)) {
                System.out.println("发布商品" + goods + "召回通知");
                recalledGoodsList.add(goods);
            }
        }
    }

}
