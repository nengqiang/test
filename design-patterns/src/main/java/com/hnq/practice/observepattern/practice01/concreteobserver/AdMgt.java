package com.hnq.practice.observepattern.practice01.concreteobserver;

import com.google.common.collect.Lists;
import com.hnq.practice.observepattern.practice01.concretesubject.DefectedGoodsList;
import com.hnq.practice.observepattern.practice01.observer.IListObserver;
import com.hnq.practice.observepattern.practice01.subject.BaseListSubject;

import java.util.List;

/**
 * ADMgt是广告管理类，实现了对商品广告的管理。对应于观察者模式的参与者，ADMgt是具体观察者ConcreteObserver。
 *
 * @author henengqiang
 * @date 2019/07/02
 */
public class AdMgt implements IListObserver {

    private String adType;

    private List<String> ads = Lists.newArrayList();

    private List<String> blacklist = Lists.newArrayList();

    public AdMgt(String adType) {
        this.adType = adType;
    }

    @Override
    public void listHasChanged(BaseListSubject baseListSubject) {
        // 仅处理问题产品清单的改变
        if (baseListSubject instanceof DefectedGoodsList) {
            // 同步广告黑名单
            blacklist = Lists.newArrayList(((DefectedGoodsList) baseListSubject).getGoodsList());
        }
    }

    public void addAd(String goods) {
        ads.add(goods);
    }

    public void showAds() {
        System.out.println("播放" + adType + "广告：");
        for (String ad : ads) {
            if (!blacklist.contains(ad)) {
                System.out.println(ad + adType + "广告");
            }
        }
    }

}
