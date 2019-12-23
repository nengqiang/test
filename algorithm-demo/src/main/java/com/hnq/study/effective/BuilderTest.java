package com.hnq.study.effective;

import java.lang.reflect.Field;
import java.math.BigDecimal;

/**
 * @author henengqiang
 * @date 2018/10/16
 */
public class BuilderTest {

    public static void main(String[] args) {
        personBuilderTest();
    }

    private static void personBuilderTest() {
        Person person = new Person.Builder()
                .setId(1)
                .setName("张三")
                .setAge(22)
                .setGender(1)
                .setMobile("15568734598")
                .setEmail("test666@qq.com")
                .setQq("1098782341")
                .setWeChat("ajax007")
                .setSalary(BigDecimal.valueOf(10000))
                .setAddress("浙江省杭州市")
                .setOrigin("北京").build();
        System.out.println(person);
        reflectToGetFields(person);
    }

    private static void reflectToGetFields(Object obj) {
        // 获取参数类
        Class cls = obj.getClass();
        // 将参数类转换为对应属性数量的 Field 类型数组（即该类有多少个属性字段 N 转换后的数组长度即为 N）
        Field[] fields = cls.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                // field.getName()得到对应字段的属性名，field.get(obj)得到对应字段属性值，field.getGenericType()得到对应字段的类型
                System.out.println("属性名：" + field.getName() +
                        "，属性值：" + field.get(obj) +
                        "，字段类型：" + field.getGenericType());
            } catch (IllegalArgumentException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

}
