package com.hnq.practice.compositepattern.practice01;

import com.hnq.practice.compositepattern.practice01.client.OrgMgt;

/**
 * 组合（Composite）实现了树形结构，使用户对单个对象和包含一组单个（或组合）对象的组合对象的处理具有一致性。
 *
 * 1 Component
 *  Component 是组件接口，声明了各组合组件对其“组成部分”的管理方法，如 Add 、Remove、GetChild 等；同时，
 * Component 还声明了组合组件和叶子组件（非组合组件）的统一业务操作。
 *  注意，在实际的应用中，Component 也可以是包含了各接口实现的普通类。这在存在多个“组合组件”类型的场景下非常常见，
 * 有利于重用“组成部分”的管理方法。
 * 2 Composite
 *  Composite 是组合组件，实现 Component 接口（或从其派生），作为组件的同时也是包含子组件的容器。
 * 3 Leaf
 *  Leaf 是叶子组件，实现 Component 接口（或从其派生），作为单独的组件实现业务方法。
 * 4 Client
 *  Client 是组合设计模式的使用者。它使用 Component 对象无差别的访问组合组件和叶子组件。
 *
 * 场景介绍:
 *  某组织机构管理模块包含对组织机构和所属人员的管理功能。其中，组织机构下可以包含子组织机构，也可以包含人员。
 * 比如本例中，组织“总公司”下包含子组织“财务部”，也可包含人员“张三”，而“财务部”包含人员“李四”。
 *
 * @author henengqiang
 * @date 2019/04/02
 */
public class Application {

    public static void main(String[] args) {
        compositeTest();
    }

    private static void compositeTest() {
        OrgMgt orgMgt = new OrgMgt();
        orgMgt.outputOrgTree(orgMgt.rootOrgName);
    }

}
