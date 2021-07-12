import com.brian.bean.Person;
import com.brian.config.MainConfigOfPropertyValues;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;


public class IOC_TEST_PropertyValues {

    @Test
    public void testValues () {
        AnnotationConfigApplicationContext app = new AnnotationConfigApplicationContext(MainConfigOfPropertyValues.class);
        Person p = (Person) app.getBean("person");
        System.out.println(p);

        ConfigurableEnvironment ce =  app.getEnvironment();


        String s =  ce.getProperty("database.name");
        System.out.println(s);





    }
}
