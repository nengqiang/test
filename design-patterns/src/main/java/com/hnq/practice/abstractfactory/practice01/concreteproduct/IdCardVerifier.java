package com.hnq.practice.abstractfactory.practice01.concreteproduct;

import com.hnq.practice.abstractfactory.practice01.abstractproduct.IIdDocVerifier;

/**
 * IdCardVerifier 类是身份证验证器，实现了 IIdDocVerifier 接口。对应于抽象工厂模式的参与者，
 * IdCardVerifier 是抽象产品 IIdDocVerifier 在身份证场景下的具体产品。
 *
 * @author henengqiang
 * @date 2019/03/07
 */
public class IdCardVerifier implements IIdDocVerifier {

    /**
     * 身份证号码
     */
    private String id;

    public IdCardVerifier(String id) {
        this.id = id;
    }

    @Override
    public boolean isIdLengthValid() {
        return id.length() == 15 || id.length() == 18;
    }

}
