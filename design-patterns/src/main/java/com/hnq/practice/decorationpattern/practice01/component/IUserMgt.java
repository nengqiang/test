package com.hnq.practice.decorationpattern.practice01.component;

/**
 * UserMgt 是用户管理接口，声明了“获取用户名”接口 getUserName。对应于装饰模式的参与者，IUserMgt 是业务类接口 Component。
 *
 * @author henengqiang
 * @date 2019/04/15
 */
public interface IUserMgt {

    String getUserName(String id);

}
