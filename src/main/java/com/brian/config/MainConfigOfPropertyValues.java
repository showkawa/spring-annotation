package com.brian.config;

import com.brian.bean.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;


@Configuration
@PropertySource(value = {"classpath:/app.yml"})
public class MainConfigOfPropertyValues {

    @Bean("person")
    public Person getPerson() {
        return new Person();
    }
}
