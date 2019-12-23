package com.hnq.practice.interpreterpattern.practice01.client;

import com.hnq.practice.interpreterpattern.practice01.abstractexp.IFilterExp;
import com.hnq.practice.interpreterpattern.practice01.context.Context;
import com.hnq.practice.interpreterpattern.practice01.nonterminalexp.AndExp;
import com.hnq.practice.interpreterpattern.practice01.nonterminalexp.OrExp;
import com.hnq.practice.interpreterpattern.practice01.terminalexp.ContainsExp;
import com.hnq.practice.interpreterpattern.practice01.terminalexp.InExp;

/**
 * FilterPlan是过滤计划类，用于载入、执行用户的过滤计划。对应于解释器模式的参与者，FilterPlan是客户Client。
 *
 * @author henengqiang
 * @date 2019/05/09
 */
public class FilterPlan {

    public static final String USER_ID = "001";

    private IFilterExp filterExp;

    private String tableName;

    public FilterPlan(String id) {
        loadPlan(id);
    }

    public void execute(Context context) {
        String sql = "select * from " + tableName + " where " + filterExp.interpretToSql(context);
        System.out.println(sql);
    }

    private void loadPlan(String id) {
        if (USER_ID.equals(id)) {
            filterExp = new AndExp(new ContainsExp("NAME", "userName"),
                    new OrExp(new InExp("ORG", "orgName"), new InExp("ROLE", "roleName")));
            tableName = "t_user";
        }
    }
}
