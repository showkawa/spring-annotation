package com.brian.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.util.StringValueResolver;

import javax.sql.DataSource;

/**
 * Profile
 *  Spring为我们提供可以根据当前环境，动态的激活和切换一系列组件和功能；
 * @Profile :指定组件在哪个环境才能注册到容器中，不指定时，任何时候都可以注册到容器中
 */
@Configuration
@PropertySource("classpath:/app.properties")
public class MainConfigOfProfile implements EmbeddedValueResolverAware {
    @Value("${database.user}")
    private String username;
    @Value("${database.jdbcUrl}")
    private String jdbcUrl;

    private StringValueResolver valueResolver;
    private String driverClass;

    @Bean("devDataSource")
    @Profile("dev")
    public DataSource dateSourceDev(@Value("${database.password}") String pwd) throws  Exception {
        ComboPooledDataSource ds =  new ComboPooledDataSource();
        ds.setUser(username);
        ds.setPassword(pwd);
        ds.setJdbcUrl(jdbcUrl);
        ds.setDriverClass(driverClass);
        System.out.println(ds.toString());
        return ds;
    }

    @Bean("testDataSource")
    @Profile("test")
    public DataSource dateSourceTest(@Value("${database.password}") String pwd) throws  Exception {
        ComboPooledDataSource ds =  new ComboPooledDataSource();
        ds.setUser(username);
        ds.setPassword(pwd);
        ds.setJdbcUrl(jdbcUrl);
        ds.setDriverClass(driverClass);
        System.out.println(ds.toString());
        return ds;
    }

    @Bean("prodDataSource")
    @Profile("prod")
    public DataSource dateSourceProd(@Value("${database.password}") String pwd) throws  Exception{
        ComboPooledDataSource ds =  new ComboPooledDataSource();
        ds.setUser(username);
        ds.setPassword(pwd);
        ds.setJdbcUrl(jdbcUrl);
        ds.setDriverClass(driverClass);
        System.out.println(ds.toString());
        return ds;
    }

    public void setEmbeddedValueResolver(StringValueResolver resolver) {
            this.valueResolver = resolver;
            driverClass = valueResolver.resolveStringValue("${database.driverClass}");
    }
}
