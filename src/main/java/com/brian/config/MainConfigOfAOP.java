package com.brian.config;


import com.brian.aop.LogAspects;
import com.brian.aop.MathCalculator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 *  AOP: [动态代理]
 *      指在程序运行时期间将某段代码切入到指定方法指定位置执行的编程方式
 *
 *      1.将业务逻辑类和切面类注入到容器中（加上@Aspect注解表示切面类 ）
 *      2.在切面类上的每个通知方法注解上注解，定义好切点
 *      3.开启基于注解的AOP模式: @EnableAspectAutoProxy
 *
 *
 * AOP 原理：
 * @EnableAspectJAutoProxy
 *      @Import(AspectJAutoProxyRegistrar.class) 给容器中导入AspectJAutoProxyRegistrar类
 *          利用AspectJAutoProxyRegistrar自定义向容器中注册bean
 *               AnnotationAwareAspectJAutoProxyCreator
 *                  ->AspectJAwareAdvisorAutoProxyCreator
 *                      ->AbstractAdvisorAutoProxyCreator
 *                          ->AbstractAutoProxyCreator
 *                              implements SmartInstantiationAwareBeanPostProcessor, BeanFactoryAware
 *                              后置处理器（在bean初始化完成前后执行） ，自动装配BeanFactory
 *
 *
 *
 */
@Configuration
@EnableAspectJAutoProxy
public class MainConfigOfAOP {

    @Bean
    public MathCalculator mathCalculator()
    {
        return new MathCalculator();
    }

    @Bean
    public LogAspects logAspects() {
        return new LogAspects();
    }
}
