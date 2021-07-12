package com.brian.bean;

import org.springframework.beans.factory.FactoryBean;


public class BrianBeanFactory  implements FactoryBean<WenTao> {
    //获取对象
    public WenTao getObject() throws Exception {
        return new WenTao();
    }
    //获取对象的类型
    public Class<?> getObjectType() {
        return WenTao.class;
    }

    //获取对象是单例模式还是原型模式
    public boolean isSingleton() {
        return true;
    }
}
