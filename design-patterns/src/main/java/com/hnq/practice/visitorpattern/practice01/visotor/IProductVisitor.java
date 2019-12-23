package com.hnq.practice.visitorpattern.practice01.visotor;

import com.hnq.practice.visitorpattern.practice01.concreteelement.AssociateProduct;
import com.hnq.practice.visitorpattern.practice01.concreteelement.SelfProduct;

/**
 * IProductVisitor是商品访问者接口，定义了访问各类型具体商品的方法。对应于访问者模式的参与者，IProductVisitor是访问者接口。
 *
 * @author henengqiang
 * @date 2019/08/08
 */
public interface IProductVisitor {

    /**
     * 访问联营商品
     * @param associateProduct
     */
    void visitAssociateProduct(AssociateProduct associateProduct);

    /**
     * 访问自营商品
     * @param selfProduct
     */
    void visitSelfProduct(SelfProduct selfProduct);

}
