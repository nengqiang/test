package com.hnq.practice.abstractfactory.practice01.abstractproduct;

import java.util.Date;

/**
 * IIdDocParser 是身份证件信息解析器接口，声明了对身份证件的类型、持有人出生日期、持有人性别、证件有效期等信息的解析。
 * 对应于抽象工厂模式的参与者，IIdDocParser 是我们的抽象产品。
 *
 * @author henengqiang
 * @date 2019/03/07
 */
public interface IIdDocParser {

    /**
     * 获取生日
     * @return  生日
     */
    Date getBirthday();

    /**
     * 获取性别
     * @return  性别："男"或"女"
     */
    String getGender();

}
