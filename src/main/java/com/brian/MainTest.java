package com.brian;

import com.brian.aop.MathCalculator;
import com.brian.bean.*;
import com.brian.config.MainConfig;
import com.brian.config.MainConfigOfLifeCycle;
import com.brian.controller.BookController;
import com.write.bmvc.annotation.BrianController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.util.Assert;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MainTest {
    public static void main(String[] args) {
         ApplicationContext mainConfig =
                 new AnnotationConfigApplicationContext(MainConfig.class);

        System.out.println("MainConfig容器创建成功");

        BookController bookController = mainConfig.getBean(BookController.class);
        try {
            Method printBook = bookController.getClass().getDeclaredMethod("printBook");
            printBook.setAccessible(true);
            printBook.invoke(bookController);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
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
        ((AnnotationConfigApplicationContext) mainConfig).close();
    }
}
