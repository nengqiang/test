package com.hnq.practice.commandpattern.practice01.invoker;

import com.google.common.collect.Lists;
import com.hnq.practice.commandpattern.practice01.command.IUserOpCommand;

import java.util.List;

/**
 * UserOpInvoker 是用户操作请求类。对应于命令模式的参与者，UserOpInvoker 是命令调用者 Invoker。
 *
 * @author henengqiang
 * @date 2019/04/26
 */
public class UserOpInvoker {

    private List<IUserOpCommand> doneCommands = Lists.newArrayList();

    public void addCommand(IUserOpCommand userOpCommand) {
        System.out.println(System.currentTimeMillis() + " 执行 " + userOpCommand.getCommandMsg());
        userOpCommand.execute();
        doneCommands.add(userOpCommand);
    }

    public void undo() {
        if (doneCommands.size() > 0) {
            IUserOpCommand undoCommand = doneCommands.get(doneCommands.size() - 1);
            System.out.println(System.currentTimeMillis() + " 撤销 " + undoCommand.getCommandMsg());
            undoCommand.cancel();
            doneCommands.remove(undoCommand);
        }
    }

}
