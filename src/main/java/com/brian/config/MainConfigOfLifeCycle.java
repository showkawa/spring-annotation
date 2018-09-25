package com.brian.config;

import com.brian.bean.Alan;
import com.brian.bean.Vincent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * bean的生命周期：
 *      bean创建---初始化---销毁的过程
 *
 *      自定义初始化和销毁的方法
 *
 *   构造（对象创建）
 *      单实例：在容器启动时创建对象
 *      多实例：在每次获取的时候创建对象
 *   初始化：
 *      对象创建完成，并赋值，调用初始化方法
 *  销毁：
 *      单实例：ioc容器关闭时销毁
 *      多实例：
 *      1).指定初始化和销毁的方法：
 *          指定init-method和destroy-method
 *      2).通过让Bean实现 Spring InitializingBean(定义初始化逻辑) DisposableBean(定义销毁逻辑)
 *      3).可以使用JSR250:
 * @PostConstruct 在bean初始化并赋值后执行
 * @Predestroy 在bean销毁时执行
 *      4）.BeanPostProcessor: bean的后置处理器
 *      postProcessBeforeInitialization：
 *      postProcessAfterInitialization
 */
@Configuration
@ComponentScan("com.brian.bean")
public class MainConfigOfLifeCycle {
//    @Scope("prototype")
/*    @Bean(initMethod = "init", destroyMethod = "destroy")
    public Alan getAlan () {
        return new Alan();
    }*/


}
