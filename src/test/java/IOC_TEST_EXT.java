import com.brian.ext.ExtConfig;
import org.junit.Test;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;



public class IOC_TEST_EXT {

    @Test
    public void test() {
        AnnotationConfigApplicationContext app = new AnnotationConfigApplicationContext(ExtConfig.class);
        //发布事件
        app.publishEvent(new ApplicationEvent(new String("自定义的事件")) {

        });
        app.close();

    }


}
