package com.write.bmybatis.sql;

import com.write.bmybatis.orm.aop.BrianInvocationHandler;

import java.lang.reflect.Proxy;

public class SqlSession {

    /**
     * 加载Mapper接口
     * @param cls
     * @param <T>
     * @return
     */
    public static <T> T getMapper(Class<T> cls){
        return (T) Proxy.newProxyInstance(cls.getClassLoader(),new Class[]{cls},new BrianInvocationHandler(cls));
    }
 }
