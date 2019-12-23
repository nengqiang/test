package com.hnq.practice.iteratorpattern.practice01.model;

import lombok.Getter;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.util.Date;

/**
 * UserRequest 是用户请求类，声明了用户请求中的编号、用户是否为VIP、请求业务类型及请求时间等属性。
 * UserRequest 在迭代器模式类图中未出现，它是集合中的元素。
 *
 * @author henengqiang
 * @date 2019/05/31
 */
@Getter
public class UserRequest {

    private String requestCode;

    private boolean isVip;

    private int type;

    private Date requestDate;

    public UserRequest(String requestCode, boolean isVip, int type) {
        this.requestCode = requestCode;
        this.isVip = isVip;
        this.type = type;
        this.requestDate = new Date();
    }

    @Override
    public String toString() {
        String typeString;
        if (type == 0) {
            typeString = "存款";
        } else if (type == 1) {
            typeString = "取款";
        } else {
            typeString = "其他";
        }

        String requestInfo = "请" + requestCode + "号";
        if (this.isVip) {
            requestInfo += "VIP";
        }
        requestInfo += "顾客到柜台办理" + typeString + "业务-" + DateFormatUtils.format(this.getRequestDate(), "yyyy-MM-dd HH:mm:ss");
        return requestInfo;
    }
}
