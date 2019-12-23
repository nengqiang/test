package com.hnq.practice.compositepattern.practice01.composite;

import com.google.common.collect.Lists;
import com.hnq.practice.compositepattern.practice01.component.IOrgComponent;

import java.util.List;

/**
 * Org 是组织机构类，实现了 IOrgComponent 接口。对应于组合模式的参与者，Org 是组合组件 Composite。
 *
 * @author henengqiang
 * @date 2019/04/02
 */
public class Org implements IOrgComponent {

    private String orgName;

    private List<IOrgComponent> childOrgComponents = Lists.newArrayList();

    public Org(String orgName) {
        this.orgName = orgName;
    }

    @Override
    public void add(IOrgComponent orgComponent) {
        childOrgComponents.add(orgComponent);
    }

    @Override
    public void remove(IOrgComponent orgComponent) {
        for (IOrgComponent childOrgComponent : childOrgComponents) {
            if (childOrgComponent.getOrgComponentName().equals(orgComponent.getOrgComponentName())) {
                childOrgComponents.remove(childOrgComponent);
            }
        }
    }

    @Override
    public List<IOrgComponent> getChildOrgComponents() {
        return childOrgComponents;
    }

    @Override
    public String getOrgComponentInfo() {
        // 组织信息
        StringBuilder orgInfo = new StringBuilder("{'NAME':" + getOrgComponentName() + ",'TYPE':'组织','CHILDREN':[");
        // 子组件
        List<IOrgComponent> childOrgComponents = getChildOrgComponents();
        for (int i = 0; i < childOrgComponents.size(); i++) {
            if (i > 0) {
                orgInfo.append(",");
            }
            orgInfo.append(childOrgComponents.get(i).getOrgComponentInfo());
        }
        orgInfo.append("]}");
        return orgInfo.toString();
    }

    @Override
    public String getOrgComponentName() {
        return orgName;
    }

}
