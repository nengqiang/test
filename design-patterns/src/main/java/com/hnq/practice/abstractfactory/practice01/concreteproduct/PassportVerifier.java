package com.hnq.practice.abstractfactory.practice01.concreteproduct;

import com.hnq.practice.abstractfactory.practice01.abstractproduct.IIdDocVerifier;

/**
 * PassportVerifier 类是护照证验证器，实现了 IIdDocVerifier 接口。对应于抽象工厂模式的参与者，
 * PassportVerifier 是抽象产品 IIdDocVerifier 在护照场景下的具体产品。
 *
 * @author henengqiang
 * @date 2019/03/07
 */
public class PassportVerifier implements IIdDocVerifier {

    /**
     * 护照编号
     */
    private String id;

    public PassportVerifier(String id) {
        this.id = id;
    }

    @Override
    public boolean isIdLengthValid() {
        return id.length() == 44;
    }

}
