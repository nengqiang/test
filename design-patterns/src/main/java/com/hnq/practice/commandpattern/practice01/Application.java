package com.hnq.practice.commandpattern.practice01;

import com.hnq.practice.commandpattern.practice01.client.UserMgt;

/**
 *  命令（Command）模式将请求的发送者与实际执行者解耦，使请求在指定的链路中传播，从而令多个对象均有机会处理同一请求。
 * 命令模式有着广泛的应用，既可用于UI中多个事件绑定同一业务操作又可对命令进行重新排序、记录日志、维护请求序列以实现撤销操作等。
 *
 * 1 Command
 *  Command 是命令接口，声明了执行命令接口方法 Execute。如果希望操作可以被撤销，则还需要声明反执行命令接口 Cancel。
 * 2 ConcreteCommand
 *  ConcreteCommand 是具体命令，实现了命令接口 Command。ConcreteCommand 需要维护命令的接收者 Receiver 对象及执行命令时的必要参数。
 * 这些变量通常都是在构造方法中指定的。ConcreteCommand 实现 Execute 接口方法时，调用 Receiver 的相应方法。如果需要实现 Cancel ，
 * 则在实现 Execute 接口方法时还需记录执行操作前的上下文以便实现 Cancel 接口方法时使用。
 * 3 Invoker
 *  Invoker 是命令调用者，负责请求的发起。Invoker 内部通常维护了 Command 类型的队列，以便在合适的时间执行命令。
 * 如果命令可以撤销，Invoker 还应提供撤销方法以撤销最后一步操作。
 * 4 Receiver
 *  Receiver 是命令接收者，是命令的实际执行者。
 * 5 Client
 *  Client 是客户类，是命令模式的使用者。Client 用适当的 Receiver 对象实例化 Command 对象并将其委托给 Invoker 调度执行。
 *
 * 场景介绍：
 *  某用户管理模块需要实现对用户的增、删、改、查操作。要求在这些操作记录日志并可以撤销。
 *
 * @author henengqiang
 * @date 2019/04/26
 */
public class Application {

    public static void main(String[] args) {
        commandTest();
    }

    private static void commandTest() {
        UserMgt userMgt = new UserMgt();
        userMgt.insertUser("001", "张三");
        userMgt.insertUser("002", "李四");
        userMgt.updateUser("001", "张三(新)");
        userMgt.deleteUser("001");
        userMgt.undo();
        userMgt.undo();
        userMgt.undo();
    }

}
