package com.hnq.practice.visitorpattern.practice01.concreteelement;

import com.hnq.practice.visitorpattern.practice01.element.BaseProduct;
import com.hnq.practice.visitorpattern.practice01.visotor.IProductVisitor;
import lombok.Getter;

/**
 * AssociateProduct是联营商品类，派生于商品抽象类BaseProduct。
 * 对应于访问者模式的参与者，AssociateProduct是具体元素类ConcreteElement。
 *
 * @author henengqiang
 * @date 2019/08/08
 */
public class AssociateProduct extends BaseProduct {

    @Getter
    private double postage;

    public AssociateProduct(String name, double price, double postage) {
        super(name, price);
        this.postage = postage;
    }

    @Override
    public void accept(IProductVisitor productVisitor) {
        productVisitor.visitAssociateProduct(this);
    }

    @Override
    public void print() {
        super.print();
        System.out.println("邮费：" + postage + "元");
    }

}
