package com.hnq.practice.builderpattern.practice01.management;

/**
 * Order 类是订单类，是生成账单的依据。虽然 Order 类不是生成器模式的参与者，
 * 但一般情况下导向器 Director 构建产品都需要一些“素材”或“依据”，只不过提供的方式不同而已。
 *
 * @author henengqiang
 * @date 2019/03/08
 */
public class Order {

    /**
     * 产品名称
     */
    private String productName;

    /**
     * 产品单价
     */
    private Float unitPrice;

    /**
     * 订单类型，0为国内订单，1为国际订单
     */
    private Integer orderType;

    /**
     * 是否为VIP
     */
    private Boolean isVIP;

    public Order() {
    }

    public Order(String productName, Float unitPrice, Integer orderType, Boolean isVIP) {
        this.productName = productName;
        this.unitPrice = unitPrice;
        this.orderType = orderType;
        this.isVIP = isVIP;
    }

    @Override
    public String toString() {
        return "Order{" +
                "productName='" + productName + '\'' +
                ", unitPrice=" + unitPrice +
                ", orderType=" + orderType +
                ", isVIP=" + isVIP +
                '}';
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Float getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Float unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    public Boolean getVIP() {
        return isVIP;
    }

    public void setVIP(Boolean VIP) {
        isVIP = VIP;
    }
}
