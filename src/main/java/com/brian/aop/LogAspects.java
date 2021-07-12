package com.brian.aop;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;

import java.lang.reflect.Array;
import java.util.Arrays;

@Aspect
public class LogAspects {

    @Pointcut("execution(public int com.brian.aop.MathCalculator.*(..))")
    public  void pointCut() { }

    @Before("com.brian.aop.LogAspects.pointCut()")
    public void logStart(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        Object[] per = joinPoint.getArgs();
        System.out.println( methodName+"()...@Before... 入参:{"+ Arrays.asList(per) +"}");
    }
    @After("com.brian.aop.LogAspects.pointCut()")
    public void logEnd(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        System.out.println(methodName+"()... @After...");
    }
    @AfterReturning(value = "com.brian.aop.LogAspects.pointCut()",returning = "re")
    public void logReturn(Object re) {
        System.out.println("@AfterReturning... 返回值：{"+ re +"}");
    }
    @AfterThrowing(value = "com.brian.aop.LogAspects.pointCut()",throwing = "ex")
    public void logThrowing(Exception ex) {
        System.out.println("@AfterThrowing... 异常信息：{"+ ex+"}");
    }
}
