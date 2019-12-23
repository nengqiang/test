package com.hnq.practice.abstractfactory.practice01.concretefactory;

import com.hnq.practice.abstractfactory.practice01.abstractfactory.IIdDocToolFactory;
import com.hnq.practice.abstractfactory.practice01.abstractproduct.IIdDocParser;
import com.hnq.practice.abstractfactory.practice01.abstractproduct.IIdDocVerifier;
import com.hnq.practice.abstractfactory.practice01.concreteproduct.PassportParser;
import com.hnq.practice.abstractfactory.practice01.concreteproduct.PassportVerifier;

/**
 * PassportToolFactory 类是护照工具工厂，实现了 IIdDocToolFactory 接口，用于创建护照的各种工具类对象。
 * 对应于抽象工厂模式的参与者，PassportToolFactory 是我们的具体工厂。
 *
 * @author henengqiang
 * @date 2019/03/07
 */
public class PassportToolFactory implements IIdDocToolFactory {

    @Override
    public IIdDocParser createIdDocParser(String id) {
        return new PassportParser(id);
    }

    @Override
    public IIdDocVerifier createIdDocVerifier(String id) {
        return new PassportVerifier(id);
    }

}
