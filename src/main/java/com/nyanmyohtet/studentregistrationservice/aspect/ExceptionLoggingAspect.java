package com.nyanmyohtet.studentregistrationservice.aspect;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ExceptionLoggingAspect {

    private static final Logger logger = LogManager.getLogger(ExceptionLoggingAspect.class);

    @Pointcut("execution(* com.nyanmyohtet.studentregistrationservice.service.*.*(..))")
    public void serviceMethods() {}

    @AfterThrowing(pointcut = "serviceMethods()", throwing = "exception")
    public void afterThrowingAdvice(JoinPoint joinPoint, Throwable exception) {
        String methodName = joinPoint.getSignature().getName();
        logger.error("Logging: {} threw an exception: {}", methodName, exception.getMessage(), exception);
    }
}
