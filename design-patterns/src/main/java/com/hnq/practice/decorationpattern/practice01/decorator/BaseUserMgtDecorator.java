package com.hnq.practice.decorationpattern.practice01.decorator;

import com.hnq.practice.decorationpattern.practice01.component.IUserMgt;

/**
 * UserMgtDecorator 是用户管理类装饰者，实现了 IUserMgt 接口。对应于装饰模式的参与者，UserMgtDecorator 是装饰者 Decorator。
 *
 * @author henengqiang
 * @date 2019/04/15
 */
public abstract class BaseUserMgtDecorator implements IUserMgt {

    private IUserMgt userMgt;

    public BaseUserMgtDecorator (IUserMgt userMgt) {
        this.userMgt = userMgt;
    }

    @Override
    public String getUserName(String id) {
        return userMgt.getUserName(id);
    }
}
