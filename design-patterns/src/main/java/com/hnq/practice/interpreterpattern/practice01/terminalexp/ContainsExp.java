package com.hnq.practice.interpreterpattern.practice01.terminalexp;

import com.hnq.practice.interpreterpattern.practice01.abstractexp.IFilterExp;
import com.hnq.practice.interpreterpattern.practice01.context.Context;

/**
 * ContainsExp是contains表达式，用于解释“contains”操作符，实现了IFilterExp接口。
 * 对应于解释器模式的参与者，ContainsExp是终结表达式TerminalExpression。
 *
 * @author henengqiang
 * @date 2019/05/09
 */
public class ContainsExp implements IFilterExp {

    private String field;

    private String valueVarName;

    public ContainsExp(String field, String valueVarName) {
        this.field = field;
        this.valueVarName = valueVarName;
    }

    @Override
    public String interpretToSql(Context context) {
        return field + " like %" + context.getVariableValue(valueVarName) + "%";
    }

}
