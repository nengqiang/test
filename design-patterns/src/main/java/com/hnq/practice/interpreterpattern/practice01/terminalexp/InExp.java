package com.hnq.practice.interpreterpattern.practice01.terminalexp;

import com.hnq.practice.interpreterpattern.practice01.abstractexp.IFilterExp;
import com.hnq.practice.interpreterpattern.practice01.context.Context;

import java.util.List;

/**
 * InExp是in表达式，用于解释“in”操作符，实现了IFilterExp接口。对应于解释器模式的参与者，InExp是终结表达式TerminalExpression。
 *
 * @author henengqiang
 * @date 2019/05/09
 */
public class InExp implements IFilterExp {

    private String field;

    private String valueVarName;

    public InExp(String field, String valueVarName) {
        this.field = field;
        this.valueVarName = valueVarName;
    }

    @SuppressWarnings("unchecked")
    @Override
    public String interpretToSql(Context context) {
        List<Object> inItems = (List<Object>) context.getVariableValue(valueVarName);
        StringBuilder sql = new StringBuilder(field + " in (");
        for (int i = 0; i < inItems.size(); i++) {
            if (i > 0) {
                sql.append(",");
            }
            sql.append(inItems.get(i));
        }
        sql.append(")");
        return sql.toString();
    }

}
