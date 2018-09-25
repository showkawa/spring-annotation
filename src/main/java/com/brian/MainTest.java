package com.brian;

import com.brian.config.MainConfigOfLifeCycle;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainTest {
    public static void main(String[] args) {
         ApplicationContext acac =
                 new AnnotationConfigApplicationContext(MainConfigOfLifeCycle.class);
        System.out.println("ioc容器创建成功");
//        Alan p =  acac.getBean(Alan.class);

        //关闭ioc容器
        ((AnnotationConfigApplicationContext) acac).close();
    }
}
