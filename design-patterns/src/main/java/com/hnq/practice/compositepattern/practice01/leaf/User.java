package com.hnq.practice.compositepattern.practice01.leaf;

import com.hnq.practice.compositepattern.practice01.component.IOrgComponent;

import java.util.List;

/**
 * User 是用户类，实现了 IOrgComponent 接口。对应于组合模式的参与者，User 是叶子组件 Leaf
 *
 * @author henengqiang
 * @date 2019/04/02
 */
public class User implements IOrgComponent {

    private String id;

    private String username;

    public User(String id, String username) {
        this.id = id;
        this.username = username;
    }

    @Override
    public void add(IOrgComponent orgComponent) {

    }

    @Override
    public void remove(IOrgComponent orgComponent) {

    }

    @Override
    public List<IOrgComponent> getChildOrgComponents() {
        return null;
    }

    @Override
    public String getOrgComponentInfo() {
        return "{'NAME':" + getOrgComponentName() + ",'TYPE':'用户','ID':" + id + "}";
    }

    @Override
    public String getOrgComponentName() {
        return username;
    }

}
