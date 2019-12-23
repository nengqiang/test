package com.hnq.practice.mediatorpattern.practice01.model;

import lombok.Getter;
import lombok.Setter;

/**
 * @author henengqiang
 * @date 2019/06/17
 */
@Getter
@Setter
public class ProductInShoppingCart {

    private String productName;

    private int count;

    /**
     * 1：正常 0：库存不足
     */
    private int state;

    public ProductInShoppingCart(String productName, int count) {
        this.productName = productName;
        this.count = count;
        this.state = 1;
    }

    @Override
    public String toString() {
        return productName + " * " + count + "，状态：" + (state == 1 ? "正常" : "库存不足");
    }
}
