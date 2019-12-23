package com.hnq.practice.factorypattern.practice01.fatherproduct;

import com.hnq.toolkit.util.DateUtils;

import java.util.Date;

/**
 * UserInfo 是用户信息类，声明了用户名、用户生日、用户享受的折扣率等用户信息，并提供了用户信息对象的序列化方法。
 * 对应于工厂方法模式的参与者，UserInfo 是我们的产品父类。
 *
 * @author henengqiang
 * @date 2019/03/11
 */
public class UserInfo {

    protected String name;

    protected Date birthday;

    protected float discountRate = 0;

    public UserInfo() {
    }

    public UserInfo(String name, Date birthday) {
        this.name = name;
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return String.format("用户名：%s\n生日：%s\n今日折扣率：%.2f%s",
                name, DateUtils.format(birthday, "yyyy-MM-dd HH:mm:ss"), discountRate * 100, "%");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public float getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(float discountRate) {
        this.discountRate = discountRate;
    }
}
