package com.hnq.practice.factorypattern.practice01.fathermgt;

import com.hnq.practice.factorypattern.practice01.fatherproduct.UserInfo;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static com.hnq.practice.factorypattern.practice01.client.UserInfoViewer.userId1;
import static com.hnq.practice.factorypattern.practice01.client.UserInfoViewer.userId2;

/**
 * UserMgt 类是用户管理类，主要声明了创建用户信息对象的方法 createUserInfo 和输出用户信息的方法 outputUserInfo。
 * 对应于工厂方法模式的参与者，UserMgt 是我们的创建者父类，createUserInfo 是工厂方法。
 *
 * @author henengqiang
 * @date 2019/03/11
 */
public class UserMgt {

    /**
     * 输出用户信息
     * @param userId    用户id
     */
    public void outputUserInfo(String userId) {
        // 用户信息
        UserInfo userInfo = createUserInfo(userId);
        System.out.println("用户信息:");
        System.out.println(userInfo);
        System.out.println("---------------------------");
    }

    /**
     * 创建用户信息对象
     * @param userId    用户id
     * @return          用户对象信息
     */
    protected UserInfo createUserInfo(String userId) {
        Object[] userBaseInfo = getUserBaseInfo(userId);
        UserInfo userInfo = new UserInfo((String) userBaseInfo[0], (Date) userBaseInfo[1]);
        // 会员日全场八折
        int membersDay = 28;
        if (isTodayTheGivenDay(membersDay, Calendar.DAY_OF_MONTH)) {
            userInfo.setDiscountRate(0.2f);
        } else {
            userInfo.setDiscountRate(0);
        }
        return userInfo;
    }

    /**
     * 获取用户信息
     * @param userId    用户id
     * @return          用户信息，item[0]为用户名，item[1]为用户生日
     */
    private Object[] getUserBaseInfo(String userId) {
        // 用户信息
        Object[] userBaseInfo = new Object[2];
        if (userId1.equals(userId)) {
            userBaseInfo[0] = "张三";
            userBaseInfo[1] = new Date(new GregorianCalendar(1985, Calendar.NOVEMBER, 12).getTimeInMillis());
        } else if (userId2.equals(userId)) {
            userBaseInfo[0] = "李四";
            userBaseInfo[1] = new Date(new GregorianCalendar(1992, Calendar.JUNE, 14).getTimeInMillis());
        }
        return userBaseInfo;
    }

    /*
     * 上述代码中展示了工厂方法经常被使用的一种模式——隐含调用。即工厂方法 createUserInfo 并没有被Client直接调用生成对象，
     * 而是在公共方法 outputUserInfo 的实现中间接调用 createUserInfo 生成具体产品。
     */

    private boolean isTodayTheGivenDay(int theDay, int field) {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(field) == theDay;
    }

}
