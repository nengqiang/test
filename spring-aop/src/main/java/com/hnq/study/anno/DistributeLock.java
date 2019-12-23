package com.hnq.study.anno;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

/**
 * @author henengqiang
 * @date 2019/11/07
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface DistributeLock {

    String key();

    long timeout() default 5;

    TimeUnit timeUnit() default TimeUnit.SECONDS;

}
