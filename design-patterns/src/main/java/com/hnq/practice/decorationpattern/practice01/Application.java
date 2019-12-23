package com.hnq.practice.decorationpattern.practice01;

import com.hnq.practice.decorationpattern.practice01.client.UserControl;

/**
 * 角色：
 * Component：
 *  业务接口，声明了业务需要实现的各种方法
 *
 * ConcreteComponent：
 *  具体的业务类，实现了Component接口。在装饰模式中，ConcreteComponent是实际被动态装饰的对象的类
 *
 * Decorator：
 *  装饰者，实现了Component接口，以使它可以像被装饰对象一样使用。Decorator包含一个Component类型的成员变量，该对象在构造方法中初始化，
 * 是被装饰的对象。Decorator在实现Component接口声明的各方法时调用被装饰对象的方法。通常来讲，Decorator被声明为抽象类。但如果只有一个
 * 具体装饰者，则无需添加这个抽象层。
 *
 * ConcreteDecorator：
 *  ConcreteDecoratorA和ConcreteDecoratorB是具体装饰者。它们从Decorator抽象类派生，实现Component接口。
 * 它们通过重写业务方法实现对被装饰类的扩展。装饰模式的扩展方式主要是指对原有业务方法的执行前后的附加操作，
 * 因此一般来说，重写的方法将首先调用装饰类自身的前置方法，再调用从Decorator继承的对应方法（实际是调用了
 * 被装饰者的方法），最后调用自身的后置方法。
 *
 * Client：
 *  装饰模式的使用者
 *
 *
 * 场景介绍：
 *  某用户控制器可以输出指定id的用户的信息。可以在业务系统中配置用户信息的缓存，有H2和Redis两种缓存可供选择。缓存不是必须的。
 *
 * @author henengqiang
 * @date 2019/04/15
 */
public class Application {

    public static void main(String[] args) {
        decoratorTest();
    }

    private static void decoratorTest() {
        UserControl userControl = new UserControl();

        userControl.setCacheType(UserControl.cacheTypeH2);
        userControl.outputUserInfo(UserControl.userId);
        userControl.outputUserInfo(UserControl.userId);

        userControl.setCacheType(UserControl.cacheTypeRedis);
        userControl.outputUserInfo(UserControl.userId);
        userControl.outputUserInfo(UserControl.userId);

        userControl.setCacheType("");
        userControl.outputUserInfo(UserControl.userId);
        userControl.outputUserInfo(UserControl.userId);
    }

}
