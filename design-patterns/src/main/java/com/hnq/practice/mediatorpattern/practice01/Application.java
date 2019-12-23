package com.hnq.practice.mediatorpattern.practice01;

import com.hnq.practice.mediatorpattern.practice01.concretecolleague.ShoppingCart;
import com.hnq.practice.mediatorpattern.practice01.concretecolleague.WareHouse;
import com.hnq.practice.mediatorpattern.practice01.concretemediator.InvChgMediator;
import com.hnq.practice.mediatorpattern.practice01.mediator.BaseInvChgMediator;

/**
 * 中介者（Mediator）模式将相关联的类对象（同事）间的交互集中于中介者对象，将各同事对象间交互的复杂度转嫁于中介者。
 *
 * @author henengqiang
 * @date 2019/06/17
 */
public class Application {

    public static void main(String[] args) {
        mediatorTest();
    }

    private static void mediatorTest() {
        // 库存改变中介者
        BaseInvChgMediator invChgMediator = new InvChgMediator();
        // 仓库A
        WareHouse wareHouseA = new WareHouse("A", invChgMediator);
        // 仓库B
        WareHouse wareHouseB = new WareHouse("B", invChgMediator);
        // 购物车1
        ShoppingCart shoppingCart1 = new ShoppingCart("1", invChgMediator);
        // 购物车2
        ShoppingCart shoppingCart2 = new ShoppingCart("2", invChgMediator);
        wareHouseA.add("面巾纸", 5);
        wareHouseB.add("面巾纸", 10);
        // 库存不足将添加失败
        shoppingCart1.addProduct("面巾纸", 20);
        // 库存充足就添加成功
        shoppingCart1.addProduct("面巾纸", 13);
        shoppingCart2.addProduct("面巾纸", 14);
        System.out.println("初始状态：");
        wareHouseA.printDetail();
        wareHouseB.printDetail();
        shoppingCart1.printDetail();
        shoppingCart2.printDetail();

        shoppingCart1.buyProduct("面巾纸");
        System.out.println("购物车1结账后：");
        wareHouseA.printDetail();
        wareHouseB.printDetail();
        shoppingCart1.printDetail();
        shoppingCart2.printDetail();
        shoppingCart1.buyProduct("面巾纸");

        wareHouseA.add("面巾纸", 12);
        System.out.println("仓库A补货后：");
        wareHouseA.printDetail();
        wareHouseB.printDetail();
        shoppingCart1.printDetail();
        shoppingCart2.printDetail();
    }

}
