package com.hnq.practice.strategypattern.service.impl;

import com.hnq.practice.strategypattern.service.IDigestStrategy;

/**
 * @author henengqiang
 * @date 2019/02/27
 */
public class SHA1StrategyImpl implements IDigestStrategy {
    @Override
    public String digest(String data) {
        return "sha1 of data";
    }
}
