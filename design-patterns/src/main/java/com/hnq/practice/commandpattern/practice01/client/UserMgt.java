package com.hnq.practice.commandpattern.practice01.client;

import com.hnq.practice.commandpattern.practice01.command.IUserOpCommand;
import com.hnq.practice.commandpattern.practice01.concretecommand.DelUserCommand;
import com.hnq.practice.commandpattern.practice01.concretecommand.InsUserCommand;
import com.hnq.practice.commandpattern.practice01.concretecommand.UdUserCommand;
import com.hnq.practice.commandpattern.practice01.invoker.UserOpInvoker;
import com.hnq.practice.commandpattern.practice01.receiver.UserDao;

/**
 * UserMgt 是用户管理类，处理用户的增、删、改操作及撤销操作。对应于命令模式的参与者，UserMgt 是客户 Client。
 *
 * @author henengqiang
 * @date 2019/04/26
 */
public class UserMgt {

    private UserOpInvoker userOpInvoker = new UserOpInvoker();

    private UserDao userDao = new UserDao();

    public void insertUser(String id, String name) {
        IUserOpCommand userOpCommand = new InsUserCommand(userDao, id, name);
        userOpInvoker.addCommand(userOpCommand);
    }

    public void deleteUser(String id) {
        IUserOpCommand userOpCommand = new DelUserCommand(userDao, id);
        userOpInvoker.addCommand(userOpCommand);
    }

    public void updateUser(String id, String name) {
        IUserOpCommand userOpCommand = new UdUserCommand(userDao, id, name);
        userOpInvoker.addCommand(userOpCommand);
    }

    /**
     * 撤销上一步操作
     */
    public void undo() {
        userOpInvoker.undo();
    }

}
