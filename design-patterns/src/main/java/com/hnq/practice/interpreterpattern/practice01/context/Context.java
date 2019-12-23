package com.hnq.practice.interpreterpattern.practice01.context;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * Context是上下文类，是解释操作执行时的参数。对应于解释器模式的参与者，Context是上下文Context。
 *
 * @author henengqiang
 * @date 2019/05/09
 */
public class Context {

    /**
     * 变量映射表
     */
    private Map<String, Object> variableMap = Maps.newHashMap();

    public Object getVariableValue(String varName) {
        if (variableMap.containsKey(varName)) {
            return variableMap.get(varName);
        }
        return null;
    }

    public void setVariableValue(String varName, Object varValue) {
        variableMap.put(varName, varValue);
    }


}
