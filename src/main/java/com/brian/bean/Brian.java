package com.brian.bean;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

//@Component
@PropertySource(value = {"classpath:/app.properties"})
public class Brian implements InitializingBean , DisposableBean {

    private WenTao wenTao;

    @Value("${database02.name}")
    private String printAttr;

    public String getPrintAttr() {
        return printAttr;
    }

    public void setPrintAttr(String printAttr) {
        this.printAttr = printAttr;
    }

    public Brian(WenTao wenTao) {
        this.wenTao = wenTao;
        System.out.println("Brian... 有参构造器...");
    }

    public WenTao getWenTao() {
        return wenTao;
    }

//    @Autowired
    public void setWenTao(WenTao wenTao) {
        this.wenTao = wenTao;
    }

    public void destroy() throws Exception {
        System.out.println("Brian... destroy...");
        System.out.println(this.printAttr);
    }

    public void afterPropertiesSet() throws Exception {
        System.out.println("Brian... init...");
    }
}
