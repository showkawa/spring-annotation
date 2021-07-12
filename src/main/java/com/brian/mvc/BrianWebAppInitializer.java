package com.brian.mvc;

import com.brian.mvc.config.AppConfig;
import com.brian.mvc.config.WebConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;


/**
 * web容器启动的时候创建对象：
 *          调用方法来初始化容器以及前端控制器
 */
public class BrianWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    /**
     * 获取根容器的配置类：(Spring的配置文件) 父容器
     * @return
     */
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{AppConfig.class};
    }

    /**
     * 获取web容器的配置类:(SpringMVC配置文件) 子容器
     * @return
     */
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{WebConfig.class};
    }

    /**
     * 获取DispatcherServlet的映射信息
     *  / => 拦截所有请求(包括静态资源)  不包括JSP
     *  /* => 拦截所有请求 包括JSP页面
     * @return
     */
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}
