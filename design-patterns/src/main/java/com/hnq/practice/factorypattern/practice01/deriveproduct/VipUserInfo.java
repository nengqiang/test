package com.hnq.practice.factorypattern.practice01.deriveproduct;

import com.hnq.practice.factorypattern.practice01.fatherproduct.UserInfo;

import java.util.Date;

/**
 * derive: 派生
 *
 * VipUserInfo 是VIP用户信息类，派生自 UserInfo 类。除了用户名、用户生日、用户享受的折扣率外，还声明了用户VIP等级等信息，
 * 同时重写了用户序列化方法。对应于工厂方法模式的参与者，VipUserInfo 是我们的具体产品类。
 *
 * @author henengqiang
 * @date 2019/03/11
 */
public class VipUserInfo extends UserInfo {

    private Integer level;

    public VipUserInfo() {
    }

    public VipUserInfo(String name, Date birthday, Integer level) {
        super(name, birthday);
        this.level = level;
    }

    public VipUserInfo(UserInfo userInfo, Integer level) {
        super(userInfo.getName(), userInfo.getBirthday());
        this.level = level;
    }

    @Override
    public String toString() {
        return super.toString() + String.format("\nVIP等级：%d", level);
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }
}
