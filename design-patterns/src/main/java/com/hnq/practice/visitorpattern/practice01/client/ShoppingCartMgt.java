package com.hnq.practice.visitorpattern.practice01.client;

import com.hnq.practice.visitorpattern.practice01.concreteelement.AssociateProduct;
import com.hnq.practice.visitorpattern.practice01.concreteelement.SelfProduct;
import com.hnq.practice.visitorpattern.practice01.objectstructure.ShoppingCart;
import com.hnq.practice.visitorpattern.practice01.visotor.impl.PriceProductVisitor;
import com.hnq.practice.visitorpattern.practice01.visotor.impl.PromoInfoFor1024ProductVisitor;

/**
 * ShoppingCartMgt是购物车管理类，用于管理购物车。对应于访问者模式的参与者，ShoppingCartMgt是客户类Client。
 *
 * @author henengqiang
 * @date 2019/08/08
 */
public class ShoppingCartMgt {

    public void print(String userId) {
        ShoppingCart shoppingCart = getShoppingChart(userId);

        PromoInfoFor1024ProductVisitor promoInfoFor1024ProductVisitor = new PromoInfoFor1024ProductVisitor();
        shoppingCart.accept(promoInfoFor1024ProductVisitor);

        PriceProductVisitor priceProductVisitor = new PriceProductVisitor();
        shoppingCart.accept(priceProductVisitor);
        shoppingCart.print();
        System.out.println("总价：" + priceProductVisitor.getPrice() + "元");
    }

    private ShoppingCart getShoppingChart(String userId) {
        ShoppingCart cart = new ShoppingCart();
        if ("001".equals(userId)) {
            cart.addProduct(new SelfProduct("空气蒸锅", 499.0, 0.2));
            cart.addProduct(new SelfProduct("扫地机器人", 1899.0, 0.1));
            cart.addProduct(new AssociateProduct("训练勺", 38, 10));
            cart.addProduct(new AssociateProduct("儿童训练器", 350, 20));
        }
        return cart;
    }

}
