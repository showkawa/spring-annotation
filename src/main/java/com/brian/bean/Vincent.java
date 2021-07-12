package com.brian.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class Vincent implements BeanPostProcessor {

    /**
     *
     * @param o
     * @param s bean初始化增强
     * @return
     * @throws BeansException
     */


    public Object postProcessBeforeInitialization(Object o, String s) throws BeansException {

        System.out.println("---postProcessBeforeInitialization:"+s);
        return new Vincent();
    }

    public Object postProcessAfterInitialization(Object o, String s) throws BeansException {
        System.out.println("---postProcessAfterInitialization:"+s);
        return new Vincent();
    }
}
