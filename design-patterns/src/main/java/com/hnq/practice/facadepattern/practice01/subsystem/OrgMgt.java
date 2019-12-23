package com.hnq.practice.facadepattern.practice01.subsystem;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * OrgMgt是组织机构管理类，提供了组织机构新增功能。对应于外观模式的参与者，OrgMgt是子系统SubSystem。
 *
 * @author henengqiang
 * @date 2019/04/16
 */
public class OrgMgt {

    private static Map<String, String> orgMap = Maps.newHashMap();

    public void add(String id, String name) {
        orgMap.put(id, name);
        orgMap.entrySet().forEach(System.out::println);
    }

}
