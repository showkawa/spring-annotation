package com.brian.tx;


import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

/**
 * 1.@EnableTransactionManagement 开启基于事务的注解管理
 * 2.配置事务管理器来管理事务
 *
 */

/**
 * 声明式事务原理
 * 1.@EnableTransactionManagement
 *      利用TransactionManagementConfigurationSelector给容器中导入两个组件
 *             AutoProxyRegistrar 和 ProxyTransactionManagementConfiguration
 * 2.AutoProxyRegistrar
 *  给容器注册一个InfrastructureAdvisorAutoProxyCreator
 *      =>利用后置处理器机制在创建对象以后，包装对象，返回一个代理对象（增强器），代理对象执行方法利用拦截器链进行调用
 * 3.ProxyTransactionManagementConfiguration
 *  3.1 给容器中注册事务增强器
 *      3.1.1 事务增强器要用事务的注解信息，AnnotationTransactionAttributeSource解析事务的注解信息
 *      3.1.2 事务拦截器
 *          TransactionInterceptor：保存了属性信息，事务管理器
 *          他是一个MethodInterceptor,在目标方法执行的时候
 *              => 执行拦截器链
 *                  事务拦截器
 *                      1，先获取事务相关的属性
 *                      2.再获取PlatformTransactionManager,如果事先没有添加指定的transactionManager,最终会从容器中
 *                      按照类型获取一个PlatformTransactionManager.
 *                      3.执行目标方法
 *                        如果异常，获取到事务管理器，利用事务管理器回滚
 *                        如果正常，获取到事务管理器，提交事务
 */
@Configuration
@ComponentScan("com.brian.tx")
@EnableTransactionManagement
public class TxConfig {

    @Bean
    public DataSource dataSource() throws PropertyVetoException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setUser("root");
        dataSource.setPassword("xJ*yABGKo3GL");
        dataSource.setDriverClass("com.mysql.jdbc.driver");
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/srtest");
        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate () throws PropertyVetoException {
        return new JdbcTemplate(dataSource());
    }


    //配置事务管理器
    @Bean
    public PlatformTransactionManager platformTransactionManager () throws PropertyVetoException {
        return new DataSourceTransactionManager(dataSource());
    }

}
