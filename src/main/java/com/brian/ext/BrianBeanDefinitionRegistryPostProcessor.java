package com.brian.ext;

import com.brian.bean.Alan;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class BrianBeanDefinitionRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor {

    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry beanDefinitionRegistry) throws BeansException {
        System.out.println("postProcessBeanDefinitionRegistry() => bean的数量 =>" + beanDefinitionRegistry.getBeanDefinitionCount());
        AbstractBeanDefinition beanDefinitionBuilder = BeanDefinitionBuilder.rootBeanDefinition(Alan.class).getBeanDefinition();
        beanDefinitionRegistry.registerBeanDefinition("alanMok",beanDefinitionBuilder);
    }

    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        System.out.println("postProcessBeanFactory() => bean的数量 => "+ configurableListableBeanFactory.getBeanDefinitionCount());
    }
}
