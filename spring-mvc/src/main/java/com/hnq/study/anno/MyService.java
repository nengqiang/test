package com.hnq.study.anno;

import java.lang.annotation.*;

/**
 * @author henengqiang
 * @date 2019/12/02
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyService {

    String value() default "";

}
