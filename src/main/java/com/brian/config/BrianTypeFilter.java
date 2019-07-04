package com.brian.config;

import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;

import java.io.IOException;

public class BrianTypeFilter implements TypeFilter {
    /*
    *
    * metadataReader：读取到的当前正在扫描类的信息
    * metadataReaderFactory： 可以获取到其他类的任何信息
    * */
    public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory) throws IOException {
        System.out.println("getClassMetadata--:" + metadataReader.getClassMetadata().getClassName());
        if(metadataReader.getClassMetadata().getClassName().contains("BrianBeanFactory"))
        {
            return true;
        }
        return false;
    }
}
