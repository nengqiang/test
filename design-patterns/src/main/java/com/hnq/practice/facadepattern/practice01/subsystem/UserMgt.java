package com.hnq.practice.facadepattern.practice01.subsystem;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * UserMgt是用户管理类，提供了用户新增功能。对应于外观模式的参与者，UserMgt是子系统SubSystem。
 *
 * @author henengqiang
 * @date 2019/04/16
 */
public class UserMgt {

    private static Map<String, String> userMap = Maps.newHashMap();

    public void add(String id, String name) {
        userMap.put(id, name);
        userMap.entrySet().forEach(System.out::println);
    }

}
