package com.write.annotation.transaction.aop;


import com.write.annotation.transaction.BrianTransaction;
import com.write.annotation.transaction.TransactionUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionStatus;

import java.lang.reflect.Method;

/**
 * 自定义事务注解
 */
@Aspect
@Component
public class BrianAopTransaction {

    @Autowired
    private TransactionUtils transactionUtils;

    @AfterThrowing("execution(* com.write.annotation.transaction.service.*.*(..))")
    public void afterThrowing(){
        transactionUtils.rollback();
    }

   @Around("execution(* com.write.annotation.transaction.service.*.*(..))")
   public void around(ProceedingJoinPoint pjp) throws Throwable {
        // 1.获取代理对象
       Method method = getMethod(pjp);
       // 2.获取该方法上是否有加上指定的注解
       TransactionStatus ts = null;
       BrianTransaction anno = method.getDeclaredAnnotation(BrianTransaction.class);
       if(anno != null){
            // 3.如果存在事务注解则开启事务注解
           ts = transactionUtils.begin();
       }
       // 4. 调用目标代理对象方法
        pjp.proceed();
       // 5.获取该方法上是否有加上指定的注解
       if(ts != null){
          // 6.如果存在事务注解则提交事务
           transactionUtils.commit(ts);
       }
   }


   private Method getMethod(ProceedingJoinPoint pjp) throws NoSuchMethodException {
       //1.1 获取方法名称
       String name = pjp.getSignature().getName();
       //1.2 获取目标对象
       Class<?> cla = pjp.getTarget().getClass();
       //1.3 获取目标对象类型
       Class[] parameterTypes = ((MethodSignature) pjp.getSignature()).getParameterTypes();
       //1.4 获取目标对象的方法
       Method method = cla.getMethod(name, parameterTypes);
       return method;
   }
}
