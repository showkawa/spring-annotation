package com.brian;

import com.brian.aop.MathCalculator;
import com.brian.bean.*;
import com.brian.config.MainConfig;
import com.brian.config.MainConfigOfLifeCycle;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainTest {
    public static void main(String[] args) {
         ApplicationContext lifeCycle =
                 new AnnotationConfigApplicationContext(MainConfigOfLifeCycle.class);

        System.out.println("LifeCycle容器创建成功");
//         Alan alan1 =  acac.getBean(Alan.class);
//        System.out.println("--ALAN--:" + alan1);
        // Alan alan2 =  acac.getBean(Alan.class);
        //System.out.println("比较两个Alan实例: " + (alan1 == alan2));

//        Person person1 = (Person) acac.getBean("person01");
//        System.out.println("---main---test---person1---: " + person1.toString());
//        Person person2 = (Person) acac.getBean("person02");
//        System.out.println("---main---test---person2---: " + person2.toString());


       // MathCalculator mathCalculator = (MathCalculator) acac.getBean("mathCalculator");
       // System.out.println("----get--mathCalculator---: " + mathCalculator);

//        BrianBeanFactory beanFactory = acac.getBean(BrianBeanFactory.class);
//        WenTao wentao = null;
//        try {
//            wentao = beanFactory.getObject();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        System.out.println("----get--WenTao---: " + wentao);


        //关闭ioc容器
        ((AnnotationConfigApplicationContext) lifeCycle).close();
    }
}
