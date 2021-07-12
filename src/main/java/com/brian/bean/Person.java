package com.brian.bean;

import org.springframework.beans.factory.annotation.Value;

public class Person {

    @Value("Tony")
    private String name;
    //@Value("19")
    private Integer age;
    @Value("${database.name}")
    private String attr;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getAttr() {
        return attr;
    }

    public void setAttr(String attr) {
        this.attr = attr;
    }

    public Person() {
    }

    public Person(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", attr='" + attr + '\'' +
                '}';
    }
}
