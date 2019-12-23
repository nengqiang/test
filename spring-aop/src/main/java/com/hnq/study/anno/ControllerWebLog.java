package com.hnq.study.anno;

import java.lang.annotation.*;

/**
 * @author henengqiang
 * @date 2019/11/07
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ControllerWebLog {

    /**
     * 所调用接口的名称
     */
    String name();

    boolean intoDb() default false;

}
