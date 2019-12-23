package com.hnq.practice.bridgingpattern.practice01.refinedabstraction;

import com.hnq.practice.bridgingpattern.practice01.abstraction.UserMgt;
import com.hnq.practice.bridgingpattern.practice01.implementor.IUserOperation;

/**
 * VipUserMgt 是VIP用户管理类，派生于 UserMgt，声明了“获取用户折扣率”方法并重写了“获取用户信息”方法。
 * 对应于桥接模式的参与者，VipUserMgt 是扩充的抽象类 RefinedAbstraction。
 *
 * @author henengqiang
 * @date 2019/03/28
 */
public class VipUserMgt extends UserMgt {

    public VipUserMgt(IUserOperation userOperation) {
        super(userOperation);
    }

    public float getUserDiscountRate(String userId) {
        return userId1.equals(userId) ? 0.2f : 0.1f;
    }

    @Override
    public String getUserInfo(String userId) {
        return super.getUserInfo(userId) + "折扣率：" + getUserDiscountRate(userId) + "。";
    }
}
