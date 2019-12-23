package com.hnq.practice.strategypattern.bean;

import com.hnq.practice.strategypattern.service.IDigestStrategy;

/**
 * @author henengqiang
 * @date 2019/02/27
 */
public class CalculateSecretkey {

    private IDigestStrategy digestStrategy;

    public CalculateSecretkey(IDigestStrategy digestStrategy) {
        super();
        this.digestStrategy = digestStrategy;
    }

    /**
     * 加密
     */
    public String calcSecStr(String value) {
        return digestStrategy.digest(value);
    }
}
