package com.hnq.practice.templatepattern.practice01;

import com.hnq.practice.templatepattern.practice01.abstractclass.BaseStorageMgt;
import com.hnq.practice.templatepattern.practice01.concreteclass.StorageMgt;

/**
 * 模板方法（Template Method）模式将组成一个过程的各子过程与该过程的实现分离。
 *
 * 1 AbstractClass
 * AbstractClass是抽象类，实现了模版方法TemplateMethod，并声明了模版方法中需要调用的抽象方法，
 * 如类图中的抽象方法PrimitiveOperation1及抽象方法PrimitiveOperation2。
 * 2 ConcreteClass
 * ConcreteClass是具体类，派生于AbstractClass，实现了AbstractClass中声明的抽象方法PrimitiveOperation1和PrimitiveOperation2。
 *
 * 场景介绍
 * 某库存管理系统包含移库功能，可以将指定数量的商品从一个仓库移至另一个仓库。
 *
 * @author henengqiang
 * @date 2019/08/07
 */
public class Application {

    public static void main(String[] args) {
        templateMethodTest();
    }

    private static void templateMethodTest() {
        BaseStorageMgt storageMgt = new StorageMgt();
        boolean resultAtoB = storageMgt.transfer("仓库A", "仓库B", "学步车", 80);
        System.out.println("从仓库A转移80件学步车到仓库B"+ (resultAtoB ? "成功" : "失败"));
        boolean resultBtoA = storageMgt.transfer("仓库B", "仓库A", "餐椅", 120);
        System.out.println("从仓库B转移120件餐椅到仓库A" + (resultBtoA ? "成功" : "失败"));
    }

}
