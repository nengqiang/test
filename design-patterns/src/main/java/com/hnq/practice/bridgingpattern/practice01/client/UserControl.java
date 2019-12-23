package com.hnq.practice.bridgingpattern.practice01.client;

import com.hnq.practice.bridgingpattern.practice01.abstraction.UserMgt;
import com.hnq.practice.bridgingpattern.practice01.concreteimplementor.InnerUserOperation;
import com.hnq.practice.bridgingpattern.practice01.concreteimplementor.OuterUserOperation;
import com.hnq.practice.bridgingpattern.practice01.implementor.IUserOperation;
import com.hnq.practice.bridgingpattern.practice01.refinedabstraction.VipUserMgt;

import java.util.UUID;

/**
 * UserControl 是用户控制器类，实现了注册组织用户功能。对应于桥接模式的参与者，UserControl 是 Client。
 *
 * @author henengqiang
 * @date 2019/03/28
 */
public class UserControl {

    public String registerOrgAndUser(String orgName, String userName, Boolean isVip, Boolean isInner) {
        IUserOperation userOperation = isInner ? new InnerUserOperation() : new OuterUserOperation();

        UserMgt userMgt = isVip ? new VipUserMgt(userOperation) : new UserMgt(userOperation);

        String orgId = UUID.randomUUID().toString();

        String userId = UUID.randomUUID().toString();

        userMgt.registerUser(userId, userName, orgId);
        return userMgt.getUserInfo(userId);
    }

}
