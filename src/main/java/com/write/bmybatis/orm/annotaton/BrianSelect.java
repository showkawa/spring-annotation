package com.write.bmybatis.orm.annotaton;


import java.lang.annotation.*;

/**
 * 自定义查询注解
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface BrianSelect {

    String value();
}
