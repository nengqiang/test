package com.hnq.practice.visitorpattern.practice01.visotor.impl;

import com.hnq.practice.visitorpattern.practice01.concreteelement.AssociateProduct;
import com.hnq.practice.visitorpattern.practice01.concreteelement.SelfProduct;
import com.hnq.practice.visitorpattern.practice01.visotor.IProductVisitor;
import lombok.Getter;

/**
 * PriceProductVisitor是价格——商品访问者类，实现了商品访问者接口IProductVisitor。价格——商品访问者用于访问并累计商品价格。
 * 对应于访问者模式的参与者，PriceProductVisitor是具体访问者ConcreteVisitorX。
 *
 * @author henengqiang
 * @date 2019/08/08
 */
public class PriceProductVisitor implements IProductVisitor {

    @Getter
    private double price = 0.0D;

    public double resetPrice() {
        return price;
    }

    @Override
    public void visitAssociateProduct(AssociateProduct associateProduct) {
        price += associateProduct.getPrice() + associateProduct.getPostage();
    }

    @Override
    public void visitSelfProduct(SelfProduct selfProduct) {
        price += selfProduct.getPrice() * (1 - selfProduct.getDiscountRate());
    }
}
