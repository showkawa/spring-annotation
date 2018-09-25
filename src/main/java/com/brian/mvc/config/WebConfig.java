package com.brian.mvc.config;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.*;

/**
 *SpringMVC容器  只扫描controller
 * useDefaultFilters = false 警用默认扫描规则（默认规则是扫描所有的）
 *
 * @EnableWebMvc 开启SpringMVC自定义配置功能
 */
@ComponentScan(value = "com.brian.mvc",includeFilters = {
        @ComponentScan.Filter(type = FilterType.ANNOTATION,classes = {Controller.class})
},useDefaultFilters = false)
@EnableWebMvc
public class WebConfig extends WebMvcConfigurerAdapter {

    /**
     * 自定义视图解析器
     * @param registry
     */
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        //registry.jsp();
        registry.jsp("/WEB-INF/views/",".jsp");
    }

    /**
     * 自定义静态资源访问
     * @param configurer
     */
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
       configurer.enable();
    }

    /**
     * 添加拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
       //registry.addInterceptor() TODO  HandlerInterceptor
    }
}
