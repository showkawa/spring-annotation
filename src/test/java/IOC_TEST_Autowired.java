import com.write.annotation.transaction.config.Config;
import com.write.annotation.transaction.service.TJsonService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

public class IOC_TEST_Autowired {

    public static void main(String[] args) throws Exception {
        AnnotationConfigApplicationContext app = new AnnotationConfigApplicationContext(Config.class);

//        Arrays.stream(app.getBeanDefinitionNames()).forEach( bean -> {
//            System.out.println("Bean Name: " + bean );
//        });
        TJsonService bean = app.getBean(TJsonService.class);
        bean.addJson();


    }





}
