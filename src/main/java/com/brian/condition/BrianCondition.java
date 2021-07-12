package com.brian.condition;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

import java.util.Iterator;
import java.util.List;

public class BrianCondition implements Condition {

    /*
    * context:判断条件能使用的上下文（环境）
    * metadata: 注释信息
    * */
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {

        System.out.println("---male:" + context.getRegistry().containsBeanDefinition("person"));
        if(context.getRegistry().containsBeanDefinition("person"))
            return false;

        return false;
    }
}
