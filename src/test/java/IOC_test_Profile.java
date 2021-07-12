import com.brian.config.MainConfigOfProfile;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.sql.DataSource;

public class IOC_test_Profile {

    @Test
    public void test() {
        /*AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(MainConfigOfProfile.class);
        String[] beanNamesForType = ac.getBeanNamesForType(DataSource.class);
        for (int i = 0; i < beanNamesForType.length; i++) {
            System.out.println(beanNamesForType[i]);
        }*/


        //设置启动的环境
        AnnotationConfigApplicationContext sc = new AnnotationConfigApplicationContext();
        sc.getEnvironment().setActiveProfiles("test","dev");
        //注册主配置
        sc.register(MainConfigOfProfile.class);
        //启动刷新容器
        sc.refresh();
        String[] beanNamesForType = sc.getBeanNamesForType(DataSource.class);
        for (int i = 0; i < beanNamesForType.length; i++) {
            System.out.println(beanNamesForType[i]);
        }


    }
}
