package com.brian.bean;

import org.springframework.beans.factory.FactoryBean;

public class BrianBeanFactory  implements FactoryBean<WenTao> {
    public WenTao getObject() throws Exception {
        return new WenTao();
    }

    public Class<?> getObjectType() {
        return WenTao.class;
    }

    public boolean isSingleton() {
        return true;
    }
}
