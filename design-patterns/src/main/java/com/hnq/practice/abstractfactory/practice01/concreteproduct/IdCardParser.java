package com.hnq.practice.abstractfactory.practice01.concreteproduct;

import com.hnq.practice.abstractfactory.practice01.abstractproduct.IIdDocParser;
import com.hnq.toolkit.consts.DateConsts;
import com.hnq.toolkit.util.DateUtils;

import java.util.Date;

/**
 * IdCardParser 类是身份证信息解析器，实现了 IIdDocParser 接口。对应于抽象工厂模式的参与者，
 * IdCardParser 是抽象产品 IIdDocParser 在身份证场景下的具体产品。
 *
 * @author henengqiang
 * @date 2019/03/07
 */
public class IdCardParser implements IIdDocParser {

    /**
     * 身份证号码
     */
    private String id;

    public IdCardParser(String id) {
        this.id = id;
    }

    @Override
    public Date getBirthday() {
        try {
            return DateUtils.parse(id.substring(6, 14), DateConsts.PATTERN_DATE_3);
        } catch (Exception e) {
            throw new RuntimeException("日期解析失败");
        }
    }

    @Override
    public String getGender() {
        return Integer.parseInt(id.substring(14, 17)) % 2 == 0 ? "女" : "男";
    }

}
