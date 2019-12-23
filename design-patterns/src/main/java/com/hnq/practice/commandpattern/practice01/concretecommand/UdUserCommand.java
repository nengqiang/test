package com.hnq.practice.commandpattern.practice01.concretecommand;

import com.hnq.practice.commandpattern.practice01.command.IUserOpCommand;
import com.hnq.practice.commandpattern.practice01.receiver.UserDao;

/**
 * UdUserCommand 是更新用户命令，实现了 IUserOpCommand 接口。对应于命令模式的参与者，UdUserCommand 是具体命令 ConcreteCommand。
 *
 * @author henengqiang
 * @date 2019/04/26
 */
public class UdUserCommand implements IUserOpCommand {

    private UserDao userDao;

    private String userId;

    private String newUserName;

    private String oldUserName;

    private boolean hasExecuted;

    public UdUserCommand(UserDao userDao, String userId, String newUserName) {
        this.userDao = userDao;
        this.userId = userId;
        this.newUserName = newUserName;
    }

    @Override
    public void execute() {
        if (!hasExecuted) {
            oldUserName = userDao.getUserName(userId);
            userDao.updateUserInfo(userId, newUserName);
            hasExecuted = true;
        }
    }

    @Override
    public void cancel() {
        if (hasExecuted) {
            userDao.updateUserInfo(userId, oldUserName);
            oldUserName = null;
            hasExecuted = false;
        }
    }

    @Override
    public String getCommandMsg() {
        return "命令：将id为" + userId + "的用户更名为" + newUserName;
    }
}
