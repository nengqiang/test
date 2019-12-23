package com.hnq.study.check;

import com.hnq.study.annotation.Validate;
import com.hnq.study.model.User;

import java.lang.reflect.Field;

/**
 * @author henengqiang
 * @date 2019/03/07
 */
public class UserCheck {

    public static boolean check(User user) {
        if (user == null) {
            System.out.println("--校验对象为空！--");
            return false;
        }

        // 获取 User 类的所有属性（如果使用 getFields，就无法获取到 private 修饰的属性）
        Field[] fields = User.class.getDeclaredFields();
        for (Field field : fields) {
            // 如果属性有注解，就进行校验
            if (field.isAnnotationPresent(Validate.class)) {
                Validate validate = field.getAnnotation(Validate.class);
                if ("gender".equals(field.getName())) {
                    if (user.getGender() == null) {
                        if (validate.isNotNull()) {
                            System.out.println("--> 性别校验不通过：不可为空！");
                            return false;
                        } else {
                            System.out.println("--> 性别可空校验通过：可以为空。");
                            continue;
                        }
                    } else {
                        System.out.println("--> 性别可空校验通过。");
                    }

                    if (user.getGender().length() < validate.min() || user.getGender().length() > validate.max()) {
                        System.out.println("--> 性别长度校验不通过！");
                        return false;
                    } else {
                        System.out.println("--> 性别长度校验通过。");
                    }
                }
                if ("name".equals(field.getName())) {
                    if (user.getName() == null) {
                        if (validate.isNotNull()) {
                            System.out.println("--> 名字校验不通过：不可为空！");
                            return false;
                        } else {
                            System.out.println("--> 名字可空校验通过：可以为空。");
                            continue;
                        }
                    } else {
                        System.out.println("--> 名字可空校验通过。");
                    }

                    if(user.getName().length() < validate.min() || user.getName().length() > validate.max()) {
                        System.out.println("--> 名字长度校验不通过！");
                        return false;
                    } else {
                        System.out.println("--> 名字长度校验通过。");
                    }
                }
            }
        }
        return true;
    }

}
