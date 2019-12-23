package com.hnq.practice.commandpattern.practice01.concretecommand;

import com.hnq.practice.commandpattern.practice01.command.IUserOpCommand;
import com.hnq.practice.commandpattern.practice01.receiver.UserDao;

/**
 * InsUserCommand 是插入用户命令，实现了 IUserOpCommand 接口。
 * 对应于命令模式的参与者，InsUserCommand 是具体命令 ConcreteCommand。
 *
 * @author henengqiang
 * @date 2019/04/26
 */
public class InsUserCommand implements IUserOpCommand {

    private UserDao userDao;

    private String userId;

    private String userName;

    private boolean hasExecuted;

    public InsUserCommand(UserDao userDao, String userId, String userName) {
        this.userDao = userDao;
        this.userId = userId;
        this.userName = userName;
    }

    @Override
    public void execute() {
        if (!hasExecuted) {
            userDao.insertUser(userId, userName);
            hasExecuted = true;
        }
    }

    @Override
    public void cancel() {
        if (hasExecuted) {
            userDao.delUser(userId);
            hasExecuted = false;
        }
    }

    @Override
    public String getCommandMsg() {
        return "命令：插入id为" + userId + "的用户";
    }
}
