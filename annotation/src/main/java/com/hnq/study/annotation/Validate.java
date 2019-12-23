package com.hnq.study.annotation;

import java.lang.annotation.*;

/**
 * @author henengqiang
 * @date 2019/03/07
 */
@Documented
@Inherited
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Validate {

    int min() default 1;

    int max() default 10;

    boolean isNotNull() default true;

}
