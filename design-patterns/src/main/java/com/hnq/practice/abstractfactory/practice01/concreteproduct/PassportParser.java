package com.hnq.practice.abstractfactory.practice01.concreteproduct;

import com.hnq.practice.abstractfactory.practice01.abstractproduct.IIdDocParser;
import com.hnq.toolkit.consts.DateConsts;
import com.hnq.toolkit.util.DateUtils;

import java.util.Date;

/**
 * PassportParser 类是护照信息解析器，实现了 IIdDocParser 接口。对应于抽象工厂模式的参与者，
 * PassportParser 是抽象产品“IIdDocParser”在护照场景下的具体产品。
 *
 * @author henengqiang
 * @date 2019/03/07
 */
public class PassportParser implements IIdDocParser {

    /**
     * 护照编号
     */
    private String id;

    public PassportParser(String id) {
        this.id = id;
    }

    @Override
    public Date getBirthday() {
        try {
            return DateUtils.parse(id.substring(13, 19), DateConsts.PATTERN_MONTH_3);
        } catch (Exception e) {
            throw new RuntimeException("日期解析失败");
        }
    }

    @Override
    public String getGender() {
        return "F".equals(id.substring(20, 21)) ? "女":"男";
    }
}
