package com.hnq.study.aop;

import com.hnq.study.anno.ControllerWebLog;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * \@Aspect: 告诉Spring将该类作为一个切面管理
 * \@Component: 说明该类是一个Spring组件
 *
 * @author henengqiang
 * @date 2019/11/07
 */
@Slf4j
@Aspect
@Component
@Order(100)
public class WebLogAspect {

    private static ThreadLocal<Map<String, Object>> threadLocal = new ThreadLocal<>();

    private static final String START_TIME = "startTime";

    private static final String REQUEST_PARAMS = "requestParams";

    /**
     * execution()              表达式的主体
     * 第一个 *                  表示返回值的类型，* 代表所有返回类型
     * com.hnq.study.controller AOP所切的服务的包名，即需要横切的业务类
     * 包名后面的..              表示当前包及子包
     * 第二个 *                  表示类名，* 表示所有类
     * 最后的.*(..)              第一个，表示任何方法名，括号内为参数类型，..代表任何类型参数
     */
    @Pointcut("execution(* com.hnq.study.controller..*.*(..))")
    public void webLog() {
    }

    @Before(value = "webLog() && @annotation(controllerWebLog)", argNames = "joinPoint,controllerWebLog")
    public void doBefore(JoinPoint joinPoint, ControllerWebLog controllerWebLog) {
        long startTime = System.currentTimeMillis();
        Map<String, Object> threadInfo = new HashMap<>();
        threadInfo.put(START_TIME, startTime);
        StringBuilder requestStr = new StringBuilder();
        Object[] args = joinPoint.getArgs();
        if (args != null && args.length > 0) {
            for (Object arg : args) {
                requestStr.append(arg.toString());
            }
        }
        threadInfo.put(REQUEST_PARAMS, requestStr.toString());
        threadLocal.set(threadInfo);
        log.info("request interface - {}, requestParams - {}", controllerWebLog.name(), threadInfo.get(REQUEST_PARAMS));
    }

    @AfterReturning(value = "webLog() && @annotation(controllerWebLog)", returning = "res")
    public void doAfterReturning(Object res, ControllerWebLog controllerWebLog) {
        Map<String, Object> threadInfo = threadLocal.get();
        long takeTime = System.currentTimeMillis() - (long) threadInfo.getOrDefault(START_TIME, System.currentTimeMillis());
        if (controllerWebLog.intoDb()) {
            // insert into db
            log.info("insert log into db");
        }
        threadLocal.remove();
        log.info("request interface - {}, cost={}ms, result={}", controllerWebLog.name(), takeTime, res);
    }

    @AfterThrowing(value = "webLog() && @annotation(controllerWebLog)", throwing = "throwable")
    public void doAfterThrowing(Throwable throwable, ControllerWebLog controllerWebLog) {
        Map<String, Object> threadInfo = threadLocal.get();
        if (controllerWebLog.intoDb()) {
            // insert into db
            log.info("insert error log into db");
        }
        threadLocal.remove();
        log.error("request interface - {} error, error log: \n", controllerWebLog.name(), throwable);
    }

}
