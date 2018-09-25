import com.brian.bean.Brian;
import com.brian.bean.WenTao;
import com.brian.config.MainConfigOfAutowired;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class IOC_TEST_Autowired {

    @Test
    public void test() {
        AnnotationConfigApplicationContext app = new AnnotationConfigApplicationContext(MainConfigOfAutowired.class);
        app.getBean(Brian.class);
        app.close();

    }


}
