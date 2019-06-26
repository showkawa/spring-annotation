package com.brian;

import com.brian.bean.Alan;
import com.brian.config.MainConfig;
import com.brian.config.MainConfigOfLifeCycle;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainTest {
    public static void main(String[] args) {
         /*ApplicationContext acac =
                 new AnnotationConfigApplicationContext(MainConfig.class);*/
         ApplicationContext acac =
                 new AnnotationConfigApplicationContext(MainConfigOfLifeCycle.class);
        System.out.println("ioc容器创建成功");
      //  Alan alan1 =  acac.getBean(Alan.class);
       // Alan alan2 =  acac.getBean(Alan.class);
        //System.out.println("比较两个Alan实例: " + (alan1 == alan2));

        //关闭ioc容器
        ((AnnotationConfigApplicationContext) acac).close();
    }
}
