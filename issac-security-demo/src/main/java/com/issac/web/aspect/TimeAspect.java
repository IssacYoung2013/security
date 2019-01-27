package com.issac.web.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * author:  ywy
 * date:    2019-01-24
 * desc:
 */
@Aspect
//@Component
@Slf4j
public class TimeAspect {

    @Around("execution(* com.issac.web.controller.UserController.*(..))")
    public Object handleControllerMethod(ProceedingJoinPoint pjp) {
        log.info("time aspect start");

        Object[] args = pjp.getArgs();
        Arrays.stream(args).forEach(arg -> log.info("arg is {}",arg));

        Object object = null;
        Long start = System.currentTimeMillis();
        try {
            object = pjp.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        log.info("time aspect 耗时: {}", System.currentTimeMillis() - start);
        log.info("time aspect end");
        return object;
    }
}
