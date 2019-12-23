package com.hnq.practice.factorypattern.practice01.client;

import com.hnq.practice.factorypattern.practice01.derivemgt.VipUserMgt;
import com.hnq.practice.factorypattern.practice01.fathermgt.UserMgt;

/**
 * UserInfoViewer 实现了用户信息的查看功能，对应于工厂方法模式的参与者，UserInfoViewer 是 Client。
 *
 * @author henengqiang
 * @date 2019/03/11
 */
public class UserInfoViewer {

    public static String userId1 = "001";

    public static String userId2 = "002";

    /**
     * 查看用户信息
     * @param userId    用户id
     */
    public void viewUserInfo(String userId) {
        // 用户管理器
        UserMgt userMgt;
        if (isVip(userId)) {
            userMgt = new VipUserMgt();
        } else {
            userMgt = new UserMgt();
        }
        userMgt.outputUserInfo(userId);
    }

    /**
     * 是否为VIP用户
     * @param userId    用户id
     * @return          是否为VIP用户
     */
    private boolean isVip(String userId) {
        return userId2.equals(userId);
    }

}
