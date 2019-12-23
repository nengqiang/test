package com.hnq.practice.factorypattern.practice01.derivemgt;

import com.hnq.practice.factorypattern.practice01.deriveproduct.VipUserInfo;
import com.hnq.practice.factorypattern.practice01.fathermgt.UserMgt;
import com.hnq.practice.factorypattern.practice01.fatherproduct.UserInfo;

import static com.hnq.practice.factorypattern.practice01.client.UserInfoViewer.userId2;

/**
 * derive: 派生
 *
 * ipUserMgt 是VIP用户管理类，派生自 UserMgt。对应于工厂方法模式的参与者，VipUserMgt 是我们的具体创建者。
 * 重写的工厂方法 createUserInfo 返回具体产品 VipUserInfo 的对象。
 *
 * @author henengqiang
 * @date 2019/03/11
 */
public class VipUserMgt extends UserMgt {

    /**
     * 创建用户信息对象
     * @param userId    用户id
     * @return          用户信息对象
     */
    @Override
    public VipUserInfo createUserInfo(String userId) {
        UserInfo userInfo = super.createUserInfo(userId);
        int level = getUserLevel(userId);
        VipUserInfo vipUserInfo = new VipUserInfo(userInfo, level);
        // VIP会员全场九折
        if (vipUserInfo.getDiscountRate() < 0.1f) {
            vipUserInfo.setDiscountRate(0.1f);
        }
        return vipUserInfo;
    }

    /**
     * 获取用户等级
     * @param id    用户id
     * @return      用户VIP等级
     */
    private int getUserLevel(String id) {
        if (userId2.equals(id)) {
            return 3;
        } else {
            return 1;
        }
    }

}
