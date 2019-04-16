package com.write.bmybatis.orm.annotaton;

import java.lang.annotation.*;

/**
 * 自定义参数注解
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface BrianParam {
    String value();
}
