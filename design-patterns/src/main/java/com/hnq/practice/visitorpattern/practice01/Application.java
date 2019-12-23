package com.hnq.practice.visitorpattern.practice01;

import com.hnq.practice.visitorpattern.practice01.client.ShoppingCartMgt;

/**
 * 访问者（Visitor）模式将一组对象与作用于这组对象的行为分离，使作用于这组对象的行为可以在不改变对象定义的情况下自由扩充。
 *
 * 1 Element
 * Element是元素抽象类，定义了元素的共有成员及其访问方法。Element还定义了抽象方法Accept，以Visitor接口类型对象为参数接受访问者访问。
 * 2 ConcreteElementX
 * ConcreteElementX是具体元素类，派生于元素抽象类Element。ConcreteElementX除了包含个性化的成员及访问成员的方法外，
 * 还实现了Element中定义的抽象方法Accept。在Accept的实现中，ConcreteElementX将自己作为参数调用Visitor接口
 * 为当前的ConcreteElementX类型定制的访问接口方法VisitConcreteElementX。
 * 3 ObjectStructure
 * ObjectStructure是对象结构类，内部维护了Element的集合及遍历方法。ObjectStructure类定义了以Visitor接口类型对象
 * 为参数接受访问者访问的方法Accept，内部实现时遍历其包含的Element，以Visitor对象为参数调用Element对象的Accept方法。
 * 4 Visitor
 * Visitor是访问者接口，为每个具体元素ConcreteElementX定义一个访问该元素的接口方法。
 * 从Visitor的接口方法与Element的实现类的耦合关系不难看出，Visitor设计模式特别适用于Element子类稳定，而Element的相关行为不稳定的场景。
 * 5 ConcreteVisitor
 * ConcreteVisitor是具体访问者类，对应一种访问目的，实现了访问者接口Visitor。
 * 请注意，这里ConcreteVisitor对应的是一种“访问目的”而非“访问者身份”。有些开发者被“具体访问者”这个参与者名称
 * 和部分设计模式教程中的示例误导，误以为ConcreteVisitor应该对应访问者身份，在业务中只有“学生”、“老师”，“普通会员”、
 * “VIP会员”这样的场景才适用于Visitor设计模式。其实，Visitor设计模式的初衷恰恰与访问者的身份无关，而仅与访问目的有关。
 * 在后面的代码实践中，读者可以看到具体访问者是如何体现“访问目的”的。
 * 6 Client
 * Client是客户类，是Visitor设计模式的使用者。Client根据业务场景，获取包含Element集合的对象结构ObjectStructure，
 * 并根据场景决定要实施在对象结构ObjectStructure中包含的各元素的访问从而实例化具体访问者ConcreteVisitor，调用ObjectStructure的Accept方法完成相应功能。
 *
 * 场景介绍
 * 某购物网站的商品分为自营和联营两种。对于不同类型的商品，价格计算方式等行为不尽相同。
 * 网站1024购物节期间对购物车功能进行了扩展，在打印商品清单的同时还加入了活动信息。
 *
 * @author henengqiang
 * @date 2019/08/08
 */
public class Application {

    public static void main(String[] args) {
        visitorTest();
    }

    private static void visitorTest() {
        ShoppingCartMgt shoppingCartMgt = new ShoppingCartMgt();
        shoppingCartMgt.print("001");
    }
}





































