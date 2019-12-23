package com.hnq.practice.builderpattern.practice01.concreteproduct;

/**
 * InternationalBill 类是国际账单类。对应于生成器模式的参与者，
 * InternationalBill 是抽象生成器 IBillBuilder 在海外购场景下的具体产品。
 *
 * 国际账单
 *
 * @author henengqiang
 * @date 2019/03/08
 */
public class InternationalBill {

    /**
     * 原价
     */
    private float basicPrice = 0;

    /**
     * 总价
     */
    private float total = 0;

    /**
     * 折扣率
     */
    private float discountRate = 0;

    /**
     * 运费
     */
    private float freight = 0;

    /**
     * 税费
     */
    private float tax = 0;

    /**
     * 序列化
     * @return  国际账单输出　
     */
    @Override
    public String toString() {
        return String.format("订单类型：海外购\n\t原价：%.2f元\n\t折扣率：%.2f%s\n\t税费：%.2f元\n\t运费：%.2f元\n\t合计：%.2f元", basicPrice, discountRate * 100, "%", tax, freight, total);
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

    public float getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(float discountRate) {
        this.discountRate = discountRate;
    }

    public float getFreight() {
        return freight;
    }

    public void setFreight(float freight) {
        this.freight = freight;
    }

    public float getTax() {
        return tax;
    }

    public void setTax(float tax) {
        this.tax = tax;
    }
}
