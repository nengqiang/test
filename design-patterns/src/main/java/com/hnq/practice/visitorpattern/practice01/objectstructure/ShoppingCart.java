package com.hnq.practice.visitorpattern.practice01.objectstructure;

import com.google.common.collect.Lists;
import com.hnq.practice.visitorpattern.practice01.element.BaseProduct;
import com.hnq.practice.visitorpattern.practice01.visotor.IProductVisitor;

import java.util.List;

/**
 * ShoppingCart是购物车类。对应于访问者模式的参与者，ShoppingCart是对象结构类ObjectStructure。
 *
 * @author henengqiang
 * @date 2019/08/08
 */
public class ShoppingCart {

    private List<BaseProduct> products = Lists.newArrayList();

    public void addProduct(BaseProduct product) {
        products.add(product);
    }

    public void accept(IProductVisitor productVisitor) {
        for (BaseProduct product : products) {
            product.accept(productVisitor);
        }
    }

    public void print() {
        System.out.println("购物车商品明细：");
        for (BaseProduct product : products) {
            product.print();
            System.out.println("------------------------------");
        }
    }

}
