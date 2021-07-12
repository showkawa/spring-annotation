package com.brian.ext;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


/**
 * 扩展原理：
 * BeanPostProcessor：bean后置处理器，bean创建对象初始化前后进行拦截的
 * BeanFactoryPostProcessor: beanFactory后置处理器
 *      在beanFactory标准初始化后调用，所有的bean定义已经保存加载到beanFactory,但bean实例还未创建
 * 1.ioc容器创建对象
 * 2.invokeBeanFactoryPostProcessors(beanFactory);执行beanFactoryPostProcessor
 *      1.直接在beanFactory中找到所有类型是BeanFactoryPostProcessor的组件，并执行他们的方法
 *      2.在初始化创建其他组件前面执行
 *
 */
@Configuration
@ComponentScan("com.brian.ext")
public class ExtConfig {


}
