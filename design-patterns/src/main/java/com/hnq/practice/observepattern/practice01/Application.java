package com.hnq.practice.observepattern.practice01;

import com.hnq.practice.observepattern.practice01.concreteobserver.AdMgt;
import com.hnq.practice.observepattern.practice01.concreteobserver.GoodsMgt;
import com.hnq.practice.observepattern.practice01.concretesubject.DefectedGoodsList;

/**
 * 1 Subject
 * Subject是目标接口，实现了对观察者的绑定方法（Attach）和解绑方法（Detach）及自身改变时对各绑定观察者的通知方法（Notify）。
 * Subject内部维护了绑定的观察者对象集合。
 * 2 ConcreteSubject
 * ConcreteSubject是具体目标，实现了目标接口Subject。ConcreteSubject声明了对内部状态的读写方法（GetState和SetState）。
 * 3 Observer
 * Observer是观察者接口，声明了被观察目标改变时的通知接口方法Update。
 * 4 ConcreteObserver
 * ConcreteObserver是具体观察者，实现了观察者接口Observer。如果一个观察者需要观察多个目标甚至观察不同类型的目标，
 * ConcreteObserver需要在实现Update接口方法时对目标对象加以区分。
 * <p>
 * 场景介绍
 * 某电商平台包含存在质量问题的产品清单、产品管理、广告（电视广告、平面广告）管理等多个模块。当有质量问题的产品加入问题清单时，
 * 平台需要发布产品召回通知并撤下该产品的所有广告。当存在质量问题的产品过检后需要恢复其广告的投放。
 *
 * @author henengqiang
 * @date 2019/07/02
 */
public class Application {

    public static void main(String[] args) {
        observerTest();
    }

    private static void observerTest() {
        // 问题产品清单
        DefectedGoodsList defectedGoodsList = new DefectedGoodsList();
        // 商品管理对象
        GoodsMgt goodsMgt = new GoodsMgt();
        defectedGoodsList.attach(goodsMgt);
        // 电视广告管理对象
        AdMgt tvAdMgt = new AdMgt("电视");
        defectedGoodsList.attach(tvAdMgt);
        // 平面广告管理对象
        AdMgt printAdMgt = new AdMgt("平面");
        defectedGoodsList.attach(printAdMgt);
        tvAdMgt.addAd("小花猫资料库");
        tvAdMgt.addAd("斑点狗购物车");
        printAdMgt.addAd("小花猫壁纸");
        printAdMgt.addAd("斑点狗桌垫");
        tvAdMgt.showAds();
        printAdMgt.showAds();

        System.out.println("向问题产品清单中加入小花猫资料库");
        defectedGoodsList.addGoods("小花猫资料库");
        tvAdMgt.showAds();
        printAdMgt.showAds();
        // 电视广告接触绑定
        defectedGoodsList.detach(tvAdMgt);
        System.out.println("从问题产品清单中将小花猫资料库移除");
        defectedGoodsList.removeGoods("小花猫资料库");
        tvAdMgt.showAds();
        printAdMgt.showAds();
    }

}
