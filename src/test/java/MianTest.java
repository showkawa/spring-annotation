import com.brian.config.MainConfig;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MianTest {

    @Test
    public  void test01 () {
      AnnotationConfigApplicationContext acac =
              new AnnotationConfigApplicationContext(MainConfig.class);
      String[] list =  acac.getBeanDefinitionNames();
        for (int i = 0; i < list.length; i++) {
            System.out.println(list[i]);

        }
    }
}
