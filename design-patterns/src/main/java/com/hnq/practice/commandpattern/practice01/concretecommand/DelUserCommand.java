package com.hnq.practice.commandpattern.practice01.concretecommand;

import com.hnq.practice.commandpattern.practice01.command.IUserOpCommand;
import com.hnq.practice.commandpattern.practice01.receiver.UserDao;

/**
 * DelUserCommand 是删除用户命令，实现了 IUserOpCommand 接口。
 * 对应于命令模式的参与者，DelUserCommand 是具体命令 ConcreteCommand。
 *
 * @author henengqiang
 * @date 2019/04/26
 */
public class DelUserCommand implements IUserOpCommand {

    private UserDao userDao;

    private String userId;

    private String userName;

    private boolean hasExecuted;

    public DelUserCommand(UserDao userDao, String userId) {
        this.userDao = userDao;
        this.userId = userId;
    }

    @Override
    public void execute() {
        if (!hasExecuted) {
            this.userName = userDao.getUserName(userId);
            userDao.delUser(userId);
            hasExecuted = true;
        }
    }

    @Override
    public void cancel() {
        if (hasExecuted) {
            userDao.insertUser(userId, userName);
            userName = null;
            hasExecuted = false;
        }
    }

    @Override
    public String getCommandMsg() {
        return "命令：删除id为" + userId + "的用户";
    }
}
