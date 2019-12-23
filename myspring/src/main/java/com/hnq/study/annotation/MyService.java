package com.hnq.study.annotation;

import java.lang.annotation.*;

/**
 * @author henengqiang
 * @date 2019/12/05
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyService {

    String value() default "";

    String name() default "";

}
