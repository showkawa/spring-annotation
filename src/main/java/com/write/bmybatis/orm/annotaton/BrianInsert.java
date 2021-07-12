package com.write.bmybatis.orm.annotaton;

import java.lang.annotation.*;

/**
 * 自定义插入注解
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface BrianInsert {

    String value();
}
