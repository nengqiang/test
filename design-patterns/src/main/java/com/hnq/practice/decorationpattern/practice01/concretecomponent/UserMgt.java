package com.hnq.practice.decorationpattern.practice01.concretecomponent;

import com.hnq.practice.decorationpattern.practice01.client.UserControl;
import com.hnq.practice.decorationpattern.practice01.component.IUserMgt;

/**
 * UserMgt 是用户管理类，实现了 IUserMgt 接口。对应于装饰模式的参与者，UserMgt 是具体业务类 ConcreteComponent。
 *
 * @author henengqiang
 * @date 2019/04/15
 */
public class UserMgt implements IUserMgt {

    @Override
    public String getUserName(String id) {
        System.out.println("debug--从数据库获取id为" + id + "的用户名");
        return UserControl.userId.equals(id) ? "张三" : "李四";
    }

}
