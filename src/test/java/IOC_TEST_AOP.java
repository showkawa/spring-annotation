import com.brian.aop.MathCalculator;
import com.brian.bean.Brian;
import com.brian.config.MainConfigOfAOP;
import com.brian.config.MainConfigOfAutowired;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class IOC_TEST_AOP {

    @Test
    public void test() {
        AnnotationConfigApplicationContext app = new AnnotationConfigApplicationContext(MainConfigOfAOP.class);
        MathCalculator mc = app.getBean(MathCalculator.class);
        mc.div(15,3);
        app.close();

    }


}
