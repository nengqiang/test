package com.hnq.practice.prototypepattern.practice01.derive;

import com.hnq.practice.prototypepattern.practice01.father.Order;

import java.util.Date;

/**
 * SalesOrder 是销售单类，派生自 Order 类。除了继承自 Order 的单据基本属性外，还包含商品名、销售数量、单价等信息。
 * SalesOrder 重写了序列化方法 toString 及拷贝方法 clone。
 * 对应于原型模式的参与者，SalesOrder 是我们的具体原型 ConcretePrototype。
 *
 * @author henengqiang
 * @date 2019/03/13
 */
public class SalesOrder extends Order {

    /**
     * 商品名
     */
    private String productName;

    /**
     * 采购数量
     */
    private Integer count;

    /**
     * 单价，为0时表示未设置，单据信息不完整
     */
    private Double unitPrice;

    public SalesOrder() {
    }

    public SalesOrder(String id, Date createTime, String creator, String productName, Integer count, Double unitPrice) {
        super(id, createTime, creator, "销售单");
        this.productName = productName;
        this.count = count;
        this.unitPrice = unitPrice;
    }

    public SalesOrder(Order order, String productName, Integer count, Double unitPrice) {
        super(order.getId(), order.getCreateTime(), order.getCreator(), "销售单");
        this.productName = productName;
        this.count = count;
        this.unitPrice = unitPrice;
    }

    @Override
    public SalesOrder clone() throws CloneNotSupportedException {
        return new SalesOrder(super.clone(), this.productName, this.count, 0D);
    }

    @Override
    public String toString() {
        return super.toString() + String.format("产品名称：%s\n数量：%s\n单价：%s\n", productName, count, unitPrice);
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

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }
}
