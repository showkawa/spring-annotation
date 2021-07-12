package com.brian.condition;

import com.brian.config.MainConfigOfAOP;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/*
* 自定义返回需要导入的组件
* */
public class BrianSelector implements ImportSelector {

    /**
     *
     * @param importingClassMetadata 当前被标记有@Import注解的所有注解信息
     * @return
     */
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {

        System.out.println("----当前配置类----:"+importingClassMetadata.getClassName());
        return new String[]{};
        //return new String[]{MainConfigOfAOP.class.getName()};
    }
}
