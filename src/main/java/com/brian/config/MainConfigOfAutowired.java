package com.brian.config;


import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

/**
 * 自动装配
 *   Spring利用依赖注入(DI),完成对IOC容器中各个组件的依赖关系赋值
 *1).@Autowired，自动注入：
 *      1.默认优先按照类型去容器中找对应的组件：applicationContext.getBean(BookDao.class);
 *      2.如果找到多个相同类型的组件，再将属性方法的名称作为组件的id去容器中查找
 *                          applicationContext.getBean("bookDao");
 *      3.@Qualifier("bookDao"):使用@Qualifier指定需要装配的组件id,而不是使用属性名
 *      4.自动装配默认一定要将属性赋值好，没有就会报错
 *              使用@Autoeired(required=false),没有默认值也不会报错
 *      5.@Primary, 让Spring进行自动装配的时候，默认使用首先的Bean
 *
 * 2).Spring还支持使用@Resource(JSR250)和@Inject(JSR330) [java规范的注解]
 * 3）.@Autowired :构造器，参数，方法，属性，
 *
 */
@EnableAspectJAutoProxy        //开启AOP代理自动配置
@EnableTransactionManagement   //基于注解的事务管理
//@ComponentScan(value = {"com.brian.bean","com.write.annotation"})
@ComponentScan(value = {"com.write.annotation.transaction"})
@Configuration
public class MainConfigOfAutowired {

        @Bean
        public DataSource dataSource() throws PropertyVetoException {
            ComboPooledDataSource dataSource = new ComboPooledDataSource();
            dataSource.setJdbcUrl("jdbc:mysql://remotemysql.com:3306/khgvUiO4eh");
            dataSource.setUser("khgvUiO4eh");
            dataSource.setPassword("BGAAee478r");
            dataSource.setDriverClass("com.mysql.jdbc.Driver");
            return dataSource;
        }

        @Bean
        public DataSourceTransactionManager platformTransactionManager() throws PropertyVetoException {
            return new DataSourceTransactionManager(this.dataSource());
        }

        @Bean
        public JdbcTemplate jdbcTemplate () throws PropertyVetoException {
            return new JdbcTemplate(this.dataSource());
        }





}
