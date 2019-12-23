package com.hnq.practice.visitorpattern.practice01.concreteelement;

import com.hnq.practice.visitorpattern.practice01.element.BaseProduct;
import com.hnq.practice.visitorpattern.practice01.visotor.IProductVisitor;
import lombok.Getter;

/**
 * SelfProduct是自营商品类，派生于商品抽象类BaseProduct。对应于访问者模式的参与者，SelfProduct是具体元素类ConcreteElement。
 *
 * @author henengqiang
 * @date 2019/08/08
 */
public class SelfProduct extends BaseProduct {

    @Getter
    private double discountRate;

    public SelfProduct(String name, double price, double discountRate) {
        super(name, price);
        this.discountRate = discountRate;
    }

    @Override
    public void accept(IProductVisitor productVisitor) {
        productVisitor.visitSelfProduct(this);
    }

    @Override
    public void print() {
        super.print();
        System.out.println("折扣率：" + discountRate);
    }

}
