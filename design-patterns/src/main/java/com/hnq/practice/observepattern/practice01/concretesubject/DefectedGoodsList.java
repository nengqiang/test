package com.hnq.practice.observepattern.practice01.concretesubject;

import com.google.common.collect.Lists;
import com.hnq.practice.observepattern.practice01.subject.BaseListSubject;

import java.util.List;

/**
 * DefectedGoodsList是问题产品清单类，实现了问题产品清单的管理。对应于观察者模式的参与者，DefectedGoodsList是具体目标ConcreteSubject。
 *
 * @author henengqiang
 * @date 2019/07/02
 */
public class DefectedGoodsList extends BaseListSubject {

    /**
     * 商品集合
     */
    private List<String> goodsList = Lists.newArrayList();

    /**
     * 添加商品并通知
     */
    public void addGoods(String goods) {
        goodsList.add(goods);
        notifyObservers();
    }

    /**
     * 删除商品并通知
     */
    public void removeGoods(String goods) {
        goodsList.remove(goods);
        notifyObservers();
    }

    /**
     * 获取商品清单
     */
    public List<String> getGoodsList() {
        return goodsList;
    }

}
