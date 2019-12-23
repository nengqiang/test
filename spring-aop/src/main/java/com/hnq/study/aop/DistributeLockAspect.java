package com.hnq.study.aop;

import com.hnq.study.anno.DistributeLock;
import com.hnq.study.enums.ErrorCodeEnum;
import com.hnq.study.model.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author henengqiang
 * @date 2019/11/07
 */
@Slf4j
@Aspect
@Component
@Order(99)
public class DistributeLockAspect {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Pointcut("execution(* com.hnq.study.controller..*.*(..))")
    public void distribute() {

    }

    @Around(value = "distribute() && @annotation(distributeLock)")
    public Object doAround(ProceedingJoinPoint joinPoint, DistributeLock distributeLock) throws Exception {
        String key = distributeLock.key();
        String keyValue = lock(key, distributeLock.timeout(), distributeLock.timeUnit());
        if (StringUtils.isEmpty(keyValue)) {
            return BaseResponse.addError(ErrorCodeEnum.OPERATE_FAILED, "请勿频繁操作");
        }
        try {
            return joinPoint.proceed();
        } catch (Throwable throwable) {
            return BaseResponse.addError(ErrorCodeEnum.SYSTEM_ERROR, "系统异常");
        } finally {
            stringRedisTemplate.delete(key);
        }
    }

    private String lock(String key, long timeout, TimeUnit timeUnit) {
        try {
            String value = UUID.randomUUID().toString();
            Boolean lockStat = stringRedisTemplate.opsForValue().setIfAbsent(key, value, timeout, timeUnit);
            if (lockStat != null && lockStat) {
                return value;
            }
            return null;
        } catch (Exception e) {
            log.error("获取分布式锁失败, key={}", key, e);
            return null;
        }
    }

}
