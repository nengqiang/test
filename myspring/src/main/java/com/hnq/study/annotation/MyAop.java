package com.hnq.study.annotation;

import java.lang.annotation.*;

/**
 * @author henengqiang
 * @date 2019/12/05
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyAop {

    String pro = "pro";

    String after = "after";

    String around = "around";

    String aopBeanName() default "";

    String aopType() default "";

}
