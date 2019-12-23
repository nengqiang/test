package com.hnq.practice.builderpattern.practice01.concreteproduct;

/**
 * DomesticBill 类是国内账单类。对应于生成器模式的参与者，
 * DomesticBill 是抽象生成器 IBillBuilder 在本地购场景下的具体产品。
 *
 * 国内账单
 *
 * @author henengqiang
 * @date 2019/03/08
 */
public class DomesticBill {

    /**
     * 原价
     */
    private float basicPrice = 0;

    /**
     * 总价
     */
    private float total = 0;

    /**
     * 折扣
     */
    private float discount = 0;

    /**
     * 运费
     */
    private float freight = 0;

    /**
     * 序列化
     * @return  国内账单输出
     */
    @Override
    public String toString() {
        return String.format("订单类型：本地\n\t原价：%.2f元\n\t折扣：%.2f元\n\t运费：%.2f元\n\t合计：%.2f元", basicPrice, discount, freight, total);
    }

    public float getBasicPrice() {
        return basicPrice;
    }

    public void setBasicPrice(float basicPrice) {
        this.basicPrice = basicPrice;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }

    public float getFreight() {
        return freight;
    }

    public void setFreight(float freight) {
        this.freight = freight;
    }
}
