package com.hnq.practice.abstractfactory.practice01.abstractfactory;

import com.hnq.practice.abstractfactory.practice01.abstractproduct.IIdDocParser;
import com.hnq.practice.abstractfactory.practice01.abstractproduct.IIdDocVerifier;

/**
 * IIdDocToolFactory 身份证件工具创建接口，声明了“创建身份证件验证器”、“创建身份证件解析器”等方法。
 * 对应于抽象工厂模式的参与者，IIdDocToolFactory 是我们的抽象工厂。
 *
 * @author henengqiang
 * @date 2019/03/07
 */
public interface IIdDocToolFactory {

    /**
     * 创建身份证件信息解析器
     * @param id    证件号码
     * @return      身份证件信息解析器对象，是抽象产品
     */
    IIdDocParser createIdDocParser(String id);

    /**
     * 创建身份证件验证器
     * @param id    证件号码
     * @return      身份证件验证器对象，是抽象产品
     */
    IIdDocVerifier createIdDocVerifier(String id);

}
