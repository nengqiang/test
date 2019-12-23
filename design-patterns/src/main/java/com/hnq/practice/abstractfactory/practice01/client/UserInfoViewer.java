package com.hnq.practice.abstractfactory.practice01.client;

import com.hnq.practice.abstractfactory.practice01.abstractfactory.IIdDocToolFactory;
import com.hnq.practice.abstractfactory.practice01.abstractproduct.IIdDocParser;
import com.hnq.practice.abstractfactory.practice01.abstractproduct.IIdDocVerifier;
import com.hnq.practice.abstractfactory.practice01.concretefactory.IdCardToolFactory;
import com.hnq.practice.abstractfactory.practice01.concretefactory.PassportToolFactory;

/**
 * 场景介绍：
 *  在某订票系统中，用户注册时需要提供有效身份证件。证件的种类可以是身份证、护照、军官证等。现在需要提供身份证件验证和身份证件信息解析
 *  两个工具以便后台管理员对注册用户的证件信息按照统一的业务逻辑进行处理。
 *
 * 用户信息查看器
 *
 * UserInfoViewer 是用户信息查看器类。对应于抽象工厂模式的参与者，UserInfoViewer 作为抽象工厂的使用者，是客户 Client。
 *
 * @author henengqiang
 * @date 2019/03/07
 */
public class UserInfoViewer {

    private String idType = "1";

    private String passportType = "2";

    public static String userId1 = "001";

    public static String userId2 = "002";

    /**
     * 获取用户证件号
     * @param userId    用户id
     * @return          证件号信息数组，其中[0]为证件类型：1为身份证，2为护照；[1]为证件号码
     */
    private String[] getUserIdDocCode(String userId) {
        // 证件信息数组
        String[] idDoc = new String[2];
        if (userId1.equals(userId)) {
            idDoc[0] = idType;
            idDoc[1] = "210102198505105335";
        } else if (userId2.equals(userId)) {
            idDoc[0] = passportType;
            idDoc[1] = "G222222224CHN8510105F180101952525252<<<<<<85";
        }
        return idDoc;
    }

    /**
     * 输出用户信息
     * @param userId    用户id
     */
    public void outputUserInfo(String userId) {
        // 证件信息数组
        String[] idDoc = getUserIdDocCode(userId);
        // 身份证件工具抽象工厂
        IIdDocToolFactory idDocToolFactory = null;
        // 根据证件类型用对应的具体工厂类实例化抽象工厂对象
        // 身份证
        if (idType.equals(idDoc[0])) {
            idDocToolFactory = new IdCardToolFactory();
        }
        // 护照
        else if (passportType.equals(idDoc[0])) {
            idDocToolFactory = new PassportToolFactory();
        }

        if (idDocToolFactory == null) {
            return;
        }
        System.out.println("证件号码："+ idDoc[1]);
        // 在实例化抽象工厂后，代码逻辑不再区分证件类型，对身份证件信息统一处理
        // 身份证件校验器
        IIdDocVerifier idDocVerifier = idDocToolFactory.createIdDocVerifier(idDoc[1]);
        if (!idDocVerifier.isIdLengthValid()) {
            System.out.println("证件信息非法");
            return;
        }
        // 身份证件信息解析器
        IIdDocParser idDocParser = idDocToolFactory.createIdDocParser(idDoc[1]);
        System.out.println("性别：" + idDocParser.getGender());
        System.out.println("生日：" + idDocParser.getBirthday());
    }

}
