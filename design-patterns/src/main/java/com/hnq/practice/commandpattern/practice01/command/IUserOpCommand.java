package com.hnq.practice.commandpattern.practice01.command;

/**
 * IUserOpCommand 是用户操作命令接口，声明了执行命令接口方法 execute 和反执行命令接口方法 Cancel。
 * 对应于命令模式的参与者，IUserOpCommand 是命令接口 Command。
 *
 * @author henengqiang
 * @date 2019/04/26
 */
public interface IUserOpCommand {

    /**
     * 执行操作
     */
    void execute();

    /**
     * 撤销操作
     */
    void cancel();

    /**
     * 获取命令描述
     * @return msg
     */
    String getCommandMsg();

}
