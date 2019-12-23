package com.hnq.practice.interpreterpattern.practice01.abstractexp;

import com.hnq.practice.interpreterpattern.practice01.context.Context;

/**
 * IFilterExp 是过滤器表达式接口，声明了“解释表达式为sql语句”方法 interpretToSql。对应于解释器模式的参与者，
 * IFilterExp 是抽象表达式 AbstractExpression。
 *
 * @author henengqiang
 * @date 2019/05/09
 */
public interface IFilterExp {

    /**
     * 解释表达式为sql语句
     */
    String interpretToSql(Context context);

}
