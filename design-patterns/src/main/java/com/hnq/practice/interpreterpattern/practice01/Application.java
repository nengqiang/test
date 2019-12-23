package com.hnq.practice.interpreterpattern.practice01;

import com.google.common.collect.Lists;
import com.hnq.practice.interpreterpattern.practice01.client.FilterPlan;
import com.hnq.practice.interpreterpattern.practice01.context.Context;

import java.util.List;

/**
 * Context:
 *  Context 是上下文，是解释器执行解释时替换操作“形参”（或者叫占位符）的数据来源。
 *
 * AbstractExpression:
 *  AbstractExpression 是抽象表达式接口，声明了解释方法 Interpret，以上下文 Context 对象为参数解释表达式。
 *
 * TerminalExpression:
 *  TerminalExpression 是终结表达式类，实现抽象表达式接口 AbstractExpression。终结表达式没有嵌套其他子表达式，可以直接进行解释。
 * 一般来说，解释器模式会为每种运算符生成一个实现 AbstractExpression 接口的表达式类。根据具体的运算形式，
 * 在构造方法中提供解释时必要的参数。在对接口方法 Interpret 的实现中，将结合传入的上下文 Context 对象对表达式进行解释。
 *
 * NonterminalExpression:
 *  NonterminalExpression 是非终结表达式类，实现抽象表达式接口 AbstractExpression。NonterminalExpression 类要解释的“运算数”
 * 不是直接变量而是子表达式。NonterminalExpression 类在构造方法中以参数形式接受作为运算数的 AbstractExpression 类型子表达式对象。
 * 实现接口方法 Interpret 时，NonterminalExpression 解释自己的运算符，填充运算数部分时调用子表达式对象的 Interpret 方法。
 *
 * Client:
 *  Client 是客户类，是解释器模式的使用者。Client 构建语法树 AbstractExpression 接口对象，
 * 以创建或传递的上下文 Context 对象为参数调用解释方法 Interpret 解释表达式。
 *
 * @author henengqiang
 * @date 2019/05/09
 */
public class Application {

    public static void main(String[] args) {
        interpreterTest();
    }

    private static void interpreterTest() {
        FilterPlan filterPlan = new FilterPlan(FilterPlan.USER_ID);
        Context context1 = new Context();
        context1.setVariableValue("userName", "张");

        List<String> org1 = Lists.newArrayList();
        org1.add("财务部");
        org1.add("法务部");
        context1.setVariableValue("orgName", org1);

        List<String> role1 = Lists.newArrayList();
        role1.add("管理员");
        role1.add("经理");
        context1.setVariableValue("roleName", role1);
        filterPlan.execute(context1);


        Context context2 = new Context();
        context2.setVariableValue("userName", "李");
        List<String> org2 = Lists.newArrayList();
        org2.add("IT部");
        org2.add("销售部");
        context2.setVariableValue("orgName", org2);

        List<String> role2 = Lists.newArrayList();
        role2.add("助理");
        context2.setVariableValue("roleName", role2);
        filterPlan.execute(context2);
    }

}
