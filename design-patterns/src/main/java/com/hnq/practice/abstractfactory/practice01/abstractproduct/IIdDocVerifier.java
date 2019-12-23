package com.hnq.practice.abstractfactory.practice01.abstractproduct;

/**
 * IIdDocVerifier 是身份证件验证器接口，声明了对身份证件的长度校验、校验位校验、联网校验等方法。
 * 对应于抽象工厂模式的参与者，IIdDocVerifier 是我们的抽象产品。
 *
 * @author henengqiang
 * @date 2019/03/07
 */
public interface IIdDocVerifier {

    /**
     * Id 号码长度是否合法
     * @return  合法为 true；不合法为 false
     */
    boolean isIdLengthValid();

}
