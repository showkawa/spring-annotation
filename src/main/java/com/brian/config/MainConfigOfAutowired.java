package com.brian.config;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 自动装配
 *   Spring利用依赖注入(DI),完成对IOC容器中各个组件的依赖关系赋值
 *1).@Autowired，自动注入：
 *      1.默认优先按照类型去容器中找对应的组件：applicationContext.getBean(BookDao.class);
 *      2.如果找到多个相同类型的组件，再将属性方法的名称作为组件的id去容器中查找
 *                          applicationContext.getBean("bookDao");
 *      3.@Qualifier("bookDao"):使用@Qualifier指定需要装配的组件id,而不是使用属性名
 *      4.自动装配默认一定要将属性赋值好，没有就会报错
 *              使用@Autoeired(required=false),没有默认值也不会报错
 *      5.@Primary, 让Spring进行自动装配的时候，默认使用首先的Bean
 *
 * 2).Spring还支持使用@Resource(JSR250)和@Inject(JSR330) [java规范的注解]
 * 3）.@Autowired :构造器，参数，方法，属性，
 *
 */
@Configuration
@ComponentScan(value = {"com.brian.bean"})
public class MainConfigOfAutowired {

}
