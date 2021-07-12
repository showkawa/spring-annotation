package com.write.annotation.transaction;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义事务注解 步骤
 *    1.定义事务注解
 *    2.封装手动事务
 *    3.定义AOP扫包事务
 *    4.在拦截方法上，使用反射技术获取拦截的方法，判断改方法是有没有指定事务注解
 *      有就开启事务
 *
 *      注意： AOP切点定义表达定义如果不正确会倒是事务不生效，但代码也不报错
 *
 *
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface BrianTransaction {

}