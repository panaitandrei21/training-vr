package com.example.demo;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
@ConditionalOnProperty(name = "intercept.enabled",
        havingValue = "true",
        matchIfMissing = true)
public class LoggedAspect {
    @Around("@within(Logged) || @annotation(Logged)")
    public Object log(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println(
                joinPoint.getSignature().getDeclaringTypeName()
                        + "." + joinPoint.getSignature().getName() +
                        " is called with " +
                        Arrays.toString(joinPoint.getArgs()));
        long t0 = System.currentTimeMillis();
        Object r = joinPoint.proceed();
        long t1 = System.currentTimeMillis();
        System.out.println("Result is: " + r+ ". Execution time: " + (t1 - t0) + "ms");
        return r;
    }
}
