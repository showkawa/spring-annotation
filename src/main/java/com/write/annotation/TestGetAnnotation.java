package com.write.annotation;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Stream;

/**
 * 测试类
 *
 * 通过JAVA反射机制 获取到注解的信息
 */
public class TestGetAnnotation {

    @BrianAnnotation(id=001,name = "Cassiel",arrayStr = {"SHE"})
    public void test1(){ }

    public void test2(){ }

    public static void main(String[] args) throws ClassNotFoundException {
        //
        Class<?> cla = Class.forName("com.write.annotation.TestGetAnnotation");
        //获取当前类（不包含继承）所有的方法
        Method[] methods = cla.getDeclaredMethods();

        Stream<Method> stream = Arrays.stream(methods);

        stream.forEach( m -> {
            System.out.println("Method Name: " + m.getName());
            BrianAnnotation anno = m.getDeclaredAnnotation(BrianAnnotation.class);
            if(anno != null) {
                System.out.println("Annotation <1>: " + anno.id());
                System.out.println("Annotation <2>: " + anno.name());
                System.out.println("Annotation <3>: " + anno.arrayStr());
            }

        });


    }

}
