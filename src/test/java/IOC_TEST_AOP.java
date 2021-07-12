import com.brian.aop.MathCalculator;
import com.brian.bean.Brian;
import com.brian.config.MainConfigOfAOP;
import com.brian.config.MainConfigOfAutowired;
import com.write.bservice.BrianAnnotationConfigApplicationContext;
import com.write.bservice.annotation.service.BaseService;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Random;

public class IOC_TEST_AOP {

    @Test
    @Ignore
    public void test() {
        AnnotationConfigApplicationContext app = new AnnotationConfigApplicationContext(MainConfigOfAOP.class);
        MathCalculator mc = app.getBean(MathCalculator.class);
        mc.div(15,3);
        app.close();
    }

    public static void main(String[] args) throws  Exception{
        BrianAnnotationConfigApplicationContext brianAnnotationConfigApplicationContext =
                new BrianAnnotationConfigApplicationContext("com.write.bservice.annotation");

        BaseService baseService = (BaseService) brianAnnotationConfigApplicationContext.getBean("baseService");
        baseService.add();

    }



}
