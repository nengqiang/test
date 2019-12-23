package com.hnq.practice.compositepattern.practice01.component;

import java.util.List;

/**
 * 组织机构组建
 *
 * @author henengqiang
 * @date 2019/04/02
 */
public interface IOrgComponent {

    /**
     * 添加子组织机构组件
     * @param orgComponent  组织机构组件
     */
    void add (IOrgComponent orgComponent);

    /**
     * 删除子组织机构组件
     * @param orgComponent  子组织机构组件
     */
    void remove(IOrgComponent orgComponent);

    /**
     * 获取子组织机构组件
     * @return  子组织机构组件集合
     */
    List<IOrgComponent> getChildOrgComponents();

    /**
     * @return  jsonStr
     */
    String getOrgComponentInfo();

    /**
     * @return  子组织机构名称
     */
    String getOrgComponentName();

}
