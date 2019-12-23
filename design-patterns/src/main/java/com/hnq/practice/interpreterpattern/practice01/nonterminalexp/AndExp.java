package com.hnq.practice.interpreterpattern.practice01.nonterminalexp;

import com.hnq.practice.interpreterpattern.practice01.abstractexp.IFilterExp;
import com.hnq.practice.interpreterpattern.practice01.context.Context;

/**
 * AndExp是and表达式，用于解释“and”操作符，实现了IFilterExp接口。对应于解释器模式的参与者，AndExp是非终结表达式NonterminalExpression。
 *
 * @author henengqiang
 * @date 2019/05/09
 */
public class AndExp implements IFilterExp {

    private IFilterExp leftExp;

    private IFilterExp rightExp;

    public AndExp(IFilterExp leftExp, IFilterExp rightExp) {
        this.leftExp = leftExp;
        this.rightExp = rightExp;
    }

    @Override
    public String interpretToSql(Context context) {
        return "(" + leftExp.interpretToSql(context) + " and " + rightExp.interpretToSql(context) + ")";
    }

}
