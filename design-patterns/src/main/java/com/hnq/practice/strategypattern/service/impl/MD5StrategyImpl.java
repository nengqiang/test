package com.hnq.practice.strategypattern.service.impl;

import com.hnq.practice.strategypattern.service.IDigestStrategy;
import org.springframework.util.DigestUtils;

/**
 * @author henengqiang
 * @date 2019/02/27
 */
public class MD5StrategyImpl implements IDigestStrategy {
    @Override
    public String digest(String data) {
        return DigestUtils.md5DigestAsHex(data.getBytes());
    }
}
