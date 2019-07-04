package com.brian.config;

import com.brian.bean.Alan;
import com.brian.bean.Brian;
import com.brian.bean.BrianBeanFactory;
import com.brian.bean.Person;
import com.brian.condition.BrianCondition;
import com.brian.condition.BrianSelector;
import com.brian.service.BookService;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.Controller;

@Configuration //告诉spring这是一个配置类
/*
* @ComponentScan
*   value:只当于扫描的的包
*   excludeFilters = 指定扫描的时候按照什么规则排除哪些组件
*   includeFilters = 指定扫描的时候只需要包含哪些组件
*   Filter.ANNOTATION:按照注解
*   Filter.ASSIGNABLE_TYPE: 按照给定的类型
* */

//@ComponentScans(value = {
//        @ComponentScan(value = "com.brian",includeFilters = {
//                @ComponentScan.Filter(type = FilterType.ANNOTATION,classes = {Controller.class}),
//                @ComponentScan.Filter(type=FilterType.ASSIGNABLE_TYPE,classes = {BookService.class}),
//                @ComponentScan.Filter(type = FilterType.CUSTOM,classes = {BrianTypeFilter.class})
//        },useDefaultFilters = false)
//})
@ComponentScan({"com.brian.service", "com.brian.controller"})
//@Import({Brian.class,Alan.class})
@Import({BrianSelector.class})
public class MainConfig {

    @Bean("person") //给容器中注册一个Bean;类型为返回值的类型；id默认是方法名作为id
    public Person person(){
        return new Person("Alan",18);
    }


    /*
    * @Conditional() 按照条件注册
    *
    * */
    @Conditional({BrianCondition.class})
    @Bean("person01")
    public Person person01() {
        return new Person("Brian",17);
    }

    @Conditional({BrianCondition.class})
    @Bean("person02")
    public Person person02() {
        return new Person("wenTao",19);
    }

    /*
    *
    *给容器中注册组件
    * 1，包扫描+ 组件标注注解（@Controller/@Service/@Repository/@Component）[自己写的方法]
    * 2， @Bean [导入的第三方包里面的组件]
    * 3，@Import [快速的给容器导入一个组件]
    *       1.@Import(要导入的组件class)
    *       2.ImportSelector：返回需要导入的组件的全类名数组
    *       3.ImportBeanDefinitionRegistrar: 手动注册bean到容器
    *  4. 使用Spring提供的FactoryBean
    * */
    @Bean
    public BrianBeanFactory brianBeanFactory() {
        return new BrianBeanFactory();
    }

}
