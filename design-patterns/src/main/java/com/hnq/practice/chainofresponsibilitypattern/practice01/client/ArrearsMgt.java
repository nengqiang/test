package com.hnq.practice.chainofresponsibilitypattern.practice01.client;

import com.google.common.collect.Maps;
import com.hnq.practice.chainofresponsibilitypattern.practice01.concretehandler.CompanyUserArrearsHandler;
import com.hnq.practice.chainofresponsibilitypattern.practice01.concretehandler.PersonalUserArrearsHandler;
import com.hnq.practice.chainofresponsibilitypattern.practice01.concretehandler.VipPersonalUserArrearsHandler;
import com.hnq.practice.chainofresponsibilitypattern.practice01.handler.BaseArrearsHandler;

import java.util.Map;

/**
 * ArrearsMgt 是欠费管理类，处理所有用户欠费事件。对应于职责链模式的参与者，ArrearsMgt 是客户 Client。
 *
 * @author henengqiang
 * @date 2019/04/24
 */
public class ArrearsMgt {

    private BaseArrearsHandler arrearsHandler;

    public ArrearsMgt() {
        arrearsHandler = new CompanyUserArrearsHandler();
        BaseArrearsHandler vipPersonalUserArrearsHandler = new VipPersonalUserArrearsHandler();
        arrearsHandler.setNextHandler(vipPersonalUserArrearsHandler);

        BaseArrearsHandler personalUserArrearsHandler = new PersonalUserArrearsHandler();
        vipPersonalUserArrearsHandler.setNextHandler(personalUserArrearsHandler);
    }

    private Map<String, Double> getArrearsUsers() {
        Map<String, Double> arrearsUsers = Maps.newHashMap();

        arrearsUsers.put("15012345432", 1200D);

        arrearsUsers.put("12386738888", 1100D);
        arrearsUsers.put("15787538888", 993D);

        arrearsUsers.put("12345656788", 120D);
        arrearsUsers.put("17856887688", 20D);
        return arrearsUsers;
    }

    public void handleArrears() {
        Map<String, Double> arrearsUsers = getArrearsUsers();
        arrearsUsers.forEach((key, value) -> arrearsHandler.handleArrears(key, value));
    }
}
