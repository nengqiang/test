package com.hnq.practice.adapterpattern.practice01.originalinterface;

import com.google.common.collect.Maps;

import java.util.Collections;
import java.util.Map;

/**
 * 这里所说的“接口”是广义的接口，实际上，Adaptee为包含各方法实现的具体类。
 * 一般来说，Adaptee 基本包含了目标接口需要的功能，但方法的名称、参数、返回值可能不同。
 *
 * @author henengqiang
 * @date 2019/03/27
 */
public class UserOperation {

    /**
     * 用户字典，key为用户名，value为用户流水
     */
    private Map<String, Integer> userMap;

    public UserOperation() {
        initUserMap();
    }

    public boolean insertUser(String name) {
        boolean result;
        try {
            userMap.put(name, getMaxUserSn() + 1);
            result = true;
        } catch (Exception e) {
            result = false;
        }
        return result;
    }

    /**
     * 获取用户流水码
     * @param name  用户名
     * @return      流水码，为0时表示用户不存在
     */
    public int getUserSn(String name) {
        return userMap.getOrDefault(name, 0);
    }

    private void initUserMap() {
        userMap = Maps.newHashMap();
        userMap.put("张三", 1);
    }

    /**
     * 获取最大流水
     * @return  最大流水号
     */
    private int getMaxUserSn() {
        return Collections.max(userMap.values());
    }
}
