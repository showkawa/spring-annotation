package com.brian.mvc.config;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;

/**
 * Spring容器  不扫描controller
 */
@ComponentScan(value = "com.brian.mvc",excludeFilters = {
        @ComponentScan.Filter(type=FilterType.ANNOTATION,classes = {Controller.class})
})
public class AppConfig {
}
