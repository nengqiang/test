package com.hnq.practice.bridgingpattern.practice01;

import com.hnq.practice.bridgingpattern.practice01.client.UserControl;

/**
 * 1 Abstraction
 * Abstraction 是抽象类，定义我们希望实现的各接口方法。这里所说的“抽象类”是指广义的抽象。其实，一般情况下 Abstraction 是
 * 包含具体实现的普通类。它包含一个实现类（或接口）类型的成员变量，通过调用该实现类的方法实现自己的方法。
 *
 * 2 RefinedAbstraction
 * RefinedAbstraction 是扩充的抽象类。它派生于 Abstraction，扩充了抽象类定义。
 *
 * 3 Implementor
 * Implementor 是实现类接口，它定义了 Abstraction 各方法中要用到的方法。
 *
 * 4 ConcreteImplementor
 * ConcreteImplementorA 和 ConcreteImplementorB 是实现类的具体类，实现了实现类接口。
 *
 * 5 Client
 * Client 是桥接设计模式的使用者。它使用 ConcreteImplementor 作为构造方法实参实例化 Abstraction，
 * 从而调用 Abstraction 的方法实现业务功能。
 *
 * 场景介绍:
 * 某电商平台“用户控制器”包含“注册组织和用户”功能。该功能需要对普通用户和vip用户分别处理。
 * 同时，对于“内部用户”和“外部用户”，底层有不同的存储逻辑。
 *
 * @author henengqiang
 * @date 2019/03/28
 */
public class Application {

    public static void main(String[] args) {
        testBridgePattern();
    }

    private static void testBridgePattern() {
        UserControl userControl = new UserControl();
        System.out.println(userControl.registerOrgAndUser("采购部", "张三", true, true));
        System.out.println("------------------------------------------------------------------------------------------");
        System.out.println(userControl.registerOrgAndUser("销售部", "李四", false, false));
    }

}
