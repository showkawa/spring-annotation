package com.brian.bean;


import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class WenTao {
    public WenTao() {
        System.out.println("wenTao... constructor...");
    }
    //bean初始化并赋值后执行
    @PostConstruct
    public void init () {
        System.out.println("wenTao... init...");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("wenTao... destroy...");
    }

}
