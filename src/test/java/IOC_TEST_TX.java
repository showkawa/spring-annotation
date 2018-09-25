import com.brian.aop.MathCalculator;
import com.brian.config.MainConfigOfAOP;
import com.brian.tx.AccessRightService;
import com.brian.tx.TxConfig;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class IOC_TEST_TX {

    @Test
    public void test() {
        AnnotationConfigApplicationContext app = new AnnotationConfigApplicationContext(TxConfig.class);
        AccessRightService accessRightService = app.getBean(AccessRightService.class);
        accessRightService.addAccessRight();
        app.close();

    }


}
