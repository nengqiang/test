package com.hnq.practice.prototypepattern.practice01.derive;

import com.hnq.practice.prototypepattern.practice01.father.Order;

import java.util.Date;

/**
 * PurchaseOrder 是采购单类，派生自 Order 类。除了继承自 Order 的单据基本属性外，还包含商品名、采购数量等信息。
 * PurchaseOrder 重写了序列化方法 toString 及拷贝方法 clone。
 * 对应于原型模式的参与者，PurchaseOrder 是我们的具体原型 ConcretePrototype。
 *
 * @author henengqiang
 * @date 2019/03/13
 */
public class PurchaseOrder extends Order {

    /**
     * 商品名
     */
    private String productName;

    /**
     * 采购数量
     */
    private Integer count;

    public PurchaseOrder() {
    }

    public PurchaseOrder(String id, Date createTime, String creator, String productName, Integer count) {
        super(id, createTime, creator, "采购单");
        this.productName = productName;
        this.count = count;
    }

    public PurchaseOrder(Order order, String productName, Integer count) {
        super(order.getId(), order.getCreateTime(), order.getCreator(), "采购单");
        this.productName = productName;
        this.count = count;
    }

    @Override
    public PurchaseOrder clone() throws CloneNotSupportedException {
        return new PurchaseOrder(super.clone(), this.productName, this.count);
    }

    @Override
    public String toString() {
        return super.toString() + String.format("产品名称：%s\n数量：%s\n", productName, count);
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
