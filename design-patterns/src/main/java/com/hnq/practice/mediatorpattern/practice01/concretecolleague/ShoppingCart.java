package com.hnq.practice.mediatorpattern.practice01.concretecolleague;

import com.google.common.collect.Lists;
import com.hnq.practice.mediatorpattern.practice01.collegue.BaseInvChgColleague;
import com.hnq.practice.mediatorpattern.practice01.mediator.BaseInvChgMediator;
import com.hnq.practice.mediatorpattern.practice01.model.ProductInShoppingCart;

import java.util.List;

/**
 * ShoppingCart 是购物车类，派生于库存改变同事类 BaseInvChgColleague。
 * ShoppingCart 重写了其感兴趣的通知处理方法并声明了自己的业务方法。
 * 对应于中介者模式的参与者，ShoppingCart 是具体同事类 ConcreteColleague。
 *
 * @author henengqiang
 * @date 2019/06/17
 */
public class ShoppingCart extends BaseInvChgColleague {

    /**
     * 产品集合
     */
    private List<ProductInShoppingCart> products = Lists.newArrayList();

    /**
     * 购物车名称
     */
    private String name;

    public ShoppingCart(String name, BaseInvChgMediator invChgMediator) {
        super(invChgMediator);
        this.name = name;
    }

    @Override
    public void inventoryChg(String productName) {
        for (ProductInShoppingCart product : products) {
            if (product.getProductName().equals(productName)) {
                if (product.getState() == 0 && invChgMediator.getTotal(productName) >= product.getCount()) {
                    product.setState(1);
                } else if (product.getState() == 1 && invChgMediator.getTotal(productName) < product.getCount()) {
                    product.setState(0);
                }
                break;
            }
        }
    }

    /**
     * 添加产品
     */
    public void addProduct (String name, int count) {
        if (invChgMediator.getTotal(name) >= count) {
            products.add(new ProductInShoppingCart(name, count));
        } else {
            System.out.println("库存不足，添加购物车失败");
        }
    }

    /**
     * 购买购物车中的产品
     */
    public void buyProduct(String name) {
        for (ProductInShoppingCart product : products) {
            if (product.getProductName().equals(name)) {
                if (product.getState() == 0) {
                    System.out.println("库存不足，链接失效");
                } else {
                    products.remove(product);
                    invChgMediator.productConsumed(product.getProductName(), product.getCount());
                }
                break;
            }
        }
    }

    /**
     * 打印明细
     */
    public void printDetail() {
        System.out.println("购物车" + name + "明细：");
        products.forEach(System.out::println);
        System.out.println("--------------------------");
    }
}
