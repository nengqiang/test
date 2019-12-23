package com.hnq.practice.visitorpattern.practice01.element;

import com.hnq.practice.visitorpattern.practice01.visotor.IProductVisitor;
import lombok.Getter;
import lombok.Setter;

/**
 * BaseProduct是抽象商品类，声明了商品的共有属性及属性的访问方法等，并定义了接受访问的接口方法。
 * 对应于访问者模式的参与者，BaseProduct是元素抽象类Element。
 *
 * @author henengqiang
 * @date 2019/08/08
 */
public abstract class BaseProduct {

    @Getter
    private String name;

    @Getter
    private double price;

    @Getter
    @Setter
    private String promotionalInfo;

    public BaseProduct(String name, double price) {
        this.name = name;
        this.price = price;
        this.promotionalInfo = "";
    }

    /**
     * accept
     * @param productVisitor
     */
    public abstract void accept(IProductVisitor productVisitor);

    public void print() {
        if (!promotionalInfo.isEmpty()) {
            System.out.println(promotionalInfo);
        }
        System.out.println("商品名称：" + name);
        System.out.println("商品价格：" + price + "元");
    }
}
