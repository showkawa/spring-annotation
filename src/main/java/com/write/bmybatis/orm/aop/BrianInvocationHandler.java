package com.write.bmybatis.orm.aop;

import com.write.bmybatis.orm.annotaton.BrianInsert;
import com.write.bmybatis.orm.annotaton.BrianParam;
import com.write.bmybatis.orm.annotaton.BrianSelect;
import com.write.bmybatis.utils.JDBCUtils;
import com.write.bmybatis.utils.SQLUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 使用反射到动态代理技术拦截接口
 */
public class BrianInvocationHandler implements InvocationHandler {

    private  Object object;

    public BrianInvocationHandler(Object object) {
        this.object = object;
    }

    /**
     *
     * @param proxy  代理对象
     * @param method 拦截的方法
     * @param args   方法上的参数
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {


        Object result;

        //insert
        result = doInsert(method, args);

        //select
        result = doQuery(method, args);

        return result;
    }


    private Object doQuery(Method method, Object[] args) throws Exception {
        //1.判断方法上是否有@BrianInsert注解
        BrianSelect select = method.getDeclaredAnnotation(BrianSelect.class);
        if (select != null) {
            //2.获取该注解的上的query语句
            String strSql = select.value();
            //3.获取参数方法上的参数和sql里面的参数匹配
            //定义一个Map集合 key为@BrianQuery的参数，value为方法参数
            //获取方法上的参数
            ConcurrentHashMap<Object, Object> paramsMap = mappingData(method, args);

            List<Object> sqlParams = new ArrayList<>();
            List<String> strings = SQLUtils.sqlSelectParameter(strSql);
            strings.stream().forEach(paramName -> {
                sqlParams.add(paramsMap.get(paramName));
            });
            //4.替换参数为?
            String newSql = SQLUtils.parameQuestion(strSql, strings);
            System.out.println("-----sql-----: " + newSql);

            //5.调用JDBC底层代码执行语句
            ResultSet query = JDBCUtils.query(newSql, sqlParams);

            //6.获取返回类型
            Class<?> returnType = method.getReturnType();


            if(!query.next()) {
                return null;
            }

            //向前移动一位
            query.previous();
            Object resultObject = returnType.newInstance();

            while(query.next()){
                for (String param: strings) {
                    //获取集合中的数据
                    Object value = query.getObject(param);
                    Field field = returnType.getDeclaredField(param);
                    field.setAccessible(true);
                    field.set(resultObject,value);
                }
            }

            return resultObject;
        }

        return null;

    }
    private int doInsert(Method method, Object[] args){
        //1.判断方法上是否有@BrianInsert注解
        BrianInsert brianInsert = method.getDeclaredAnnotation(BrianInsert.class);
        if(brianInsert != null){
            //2.获取该注解的上的insert语句
            String strSql = brianInsert.value();
            //3.获取参数方法上的参数和sql里面的参数匹配
            //定义一个Map集合 key为@BrianInsert的参数，value为方法参数
            //获取方法上的参数
            ConcurrentHashMap<Object,Object> paramsMap = mappingData(method, args);

            List<Object> sqlParams = new ArrayList<>();
            String[] strings = SQLUtils.sqlInsertParameter(strSql);
            Arrays.stream(strings).forEach(paramName -> {
                sqlParams.add(paramsMap.get(paramName));
            });
            //4.替换参数为?
            String newSql = SQLUtils.parameQuestion(strSql, strings);
            System.out.println("-----sql-----: " + newSql);

            //5.调用JDBC底层代码执行语句
            return JDBCUtils.insert(newSql,false,sqlParams);
        }
        return 0;
    }

    private ConcurrentHashMap<Object,Object> mappingData(Method method, Object[] args){
        ConcurrentHashMap<Object,Object> paramsMaps = new ConcurrentHashMap<>();
        Parameter[] parameters = method.getParameters();
        for (int i = 0; i < parameters.length; i++) {
            Parameter parameter = parameters[i];
            BrianParam brianParam = parameter.getAnnotation(BrianParam.class);
            if(brianParam != null) {
                //参数名称
                String paramName = brianParam.value();
                Object paramValue = args[i];
                paramsMaps.put(paramName,paramValue);
            }
        }
        return paramsMaps;
    }
}
