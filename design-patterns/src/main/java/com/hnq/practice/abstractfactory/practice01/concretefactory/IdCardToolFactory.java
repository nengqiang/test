package com.hnq.practice.abstractfactory.practice01.concretefactory;

import com.hnq.practice.abstractfactory.practice01.abstractfactory.IIdDocToolFactory;
import com.hnq.practice.abstractfactory.practice01.abstractproduct.IIdDocParser;
import com.hnq.practice.abstractfactory.practice01.abstractproduct.IIdDocVerifier;
import com.hnq.practice.abstractfactory.practice01.concreteproduct.IdCardParser;
import com.hnq.practice.abstractfactory.practice01.concreteproduct.IdCardVerifier;

/**
 * IdCardToolFactory 类是身份证工具工厂，实现了 IIdDocToolFactory 接口，用于创建身份证的各种工具类对象。
 * 对应于抽象工厂模式的参与者，IdCardToolFactory 是我们的具体工厂。
 *
 * @author henengqiang
 * @date 2019/03/07
 */
public class IdCardToolFactory implements IIdDocToolFactory {

    @Override
    public IIdDocParser createIdDocParser(String id) {
        return new IdCardParser(id);
    }

    @Override
    public IIdDocVerifier createIdDocVerifier(String id) {
        return new IdCardVerifier(id);
    }

}
