package com.hnq.practice.visitorpattern.practice01.visotor.impl;

import com.hnq.practice.visitorpattern.practice01.concreteelement.AssociateProduct;
import com.hnq.practice.visitorpattern.practice01.concreteelement.SelfProduct;
import com.hnq.practice.visitorpattern.practice01.visotor.IProductVisitor;

/**
 * PromoInfoFor1024ProductVisitor是1024购物节促销商品访问者类，实现了商品访问者接口IProductVisitor。
 * 1024购物节促销商品访问者用于访问并设置商品促销信息。对应于访问者模式的参与者，PromoInfoFor1024ProductVisitor是具体访问者ConcreteVisitor。
 *
 * @author henengqiang
 * @date 2019/08/08
 */
public class PromoInfoFor1024ProductVisitor implements IProductVisitor {

    @Override
    public void visitAssociateProduct(AssociateProduct associateProduct) {
        associateProduct.setPromotionalInfo("1024购物节大展销");
    }

    @Override
    public void visitSelfProduct(SelfProduct selfProduct) {
        selfProduct.setPromotionalInfo("1024购物节自营商品大展销");
    }
}
