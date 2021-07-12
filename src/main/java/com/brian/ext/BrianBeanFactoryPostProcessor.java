package com.brian.ext;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class BrianBeanFactoryPostProcessor implements BeanFactoryPostProcessor {


    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        System.out.println("BrianBeanFactoryPostProcessor.postProcessBeanFactory()...");
        String[] beanDefinitionNames = configurableListableBeanFactory.getBeanDefinitionNames();
        int count = configurableListableBeanFactory.getBeanDefinitionCount();
        System.out.println("Beans Name  => " + Arrays.asList(beanDefinitionNames));
        System.out.println("Beans Count => " + count);
    }
}
