package com.write.bmvc.servlet;

import com.write.bmvc.annotation.BrianController;
import com.write.bmvc.annotation.BrianRequestMapping;
import com.write.bservice.annotation.utils.ClassUtil;
import org.springframework.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 自定义前端控制器
 *
 * 1.创建一个前端控制器  BrianDispatcherServlet 拦截所有请求   （基于servlet实现）
 * 2.初始化操作 重写servlet init()方法
 * ####2.1 扫描包，将包下范围所有的类，注入到springMVC容器里面去
 * ####2.2 将URL映射和方法关联
 * #######2.2.1 判断类上是否有注解，通过java反射机循环遍历方法，判断方法上是否有注解，进行封装URL和方法对应
 * 3.处理请求，重写servlet的doGet()或者doPost()方法
 * ####3.1 获取请求url, 去urlBeans获取实例对象，成功湖区实例后，调用urlMethods获取方法名称，使用java反射机制执行方法
 *
 */
public class BrianDispatcherServlet extends HttpServlet {

    //springMVC 容器  {key:类名Id, value:对象}
    private ConcurrentHashMap<String,Object> springmvcBeans = new ConcurrentHashMap<>();
    //springMVC 容器  {key:url请求地址, value: Controller对象}
    private ConcurrentHashMap<String,Object> urlBeans = new ConcurrentHashMap<>();
    //springMVC 容器  {key:url请求地址, value: Controller对象里面的方法的名称}
    private ConcurrentHashMap<String,String> urlMethods = new ConcurrentHashMap<>();

    @Override
    public void init() throws ServletException {
        //1. 扫描当前包下所有的类
        List<Class<?>> classes = ClassUtil.getClasses("com.write.bmvc.controller");
        //2.将类放到springMVC容器中
        putIntoMvcContainer(classes);
        //3.将url和方法进行关联
        handlerMapping();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
       //处理请求
       //1.获取请求的url地址
        String requestURI = req.getRequestURI();
        if(StringUtils.isEmpty(requestURI)){
            return;
        }
        //2.从Map集合中获取控制对象
        Object obj = urlBeans.get(requestURI);
        if(obj != null){
            resp.getWriter().write("url not found 404!!!");
            return;
        }
        //3.使用url获取方法
        String methodName = urlMethods.get(requestURI);
        if(StringUtils.isEmpty(methodName)){
            resp.getWriter().write("method not found 404!!!");
        }
        //4.使用java机制执行方法
        String resultPage = (String) methodInvoke(obj, methodName);
        resp.getWriter().write(resultPage);
        //5.使用java反射机制获取方法的返回结果
       //6.调用试图转换器渲染给页面展示






    }


    private Object methodInvoke(Object obj,String methodName){
        Class<?> cls = obj.getClass();
        Method method = null;
        try {
            method = cls.getDeclaredMethod(methodName);
            Object result = method.invoke(obj);
            return result;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }


    }

    private void putIntoMvcContainer(List<Class<?>> classes){
            classes.stream().forEach(cla -> {
                //判断类上是否有加注解
                BrianController declaredAnnotation = cla.getDeclaredAnnotation(BrianController.class);
                if(declaredAnnotation != null) {
                    String beanId = ClassUtil.toLowerCaseFirstOne(cla.getSimpleName());
                    try {
                        Object obj = newInstance(cla);
                        springmvcBeans.put(beanId,obj);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
    }

    //实例化对象
    private Object newInstance(Class<?> cla) throws Exception {
         return cla.newInstance();
    }

    private void handlerMapping(){
        //1.遍历springMVC容器，判断Bean对象上是否有@BrianRequestMapping url映射注解
        springmvcBeans.entrySet().stream().forEach(bean -> {
            Object value = bean.getValue();
            //判断类上是否有加注解
            BrianRequestMapping declaredAnnotation = value.getClass().getDeclaredAnnotation(BrianRequestMapping.class);
            String baseUrl = "";
            if(declaredAnnotation != null){
                //获取类上的url映射地址
                baseUrl = declaredAnnotation.value().toString();
            }
            //判断类里面的方法是否有加注解
            String finalBaseUrl = baseUrl;
            Arrays.stream(value.getClass().getFields()).forEach(method -> {
                BrianRequestMapping declaredAnnotation1 = method.getDeclaredAnnotation(BrianRequestMapping.class);
                String methodUrl = "";
                if(declaredAnnotation1 != null) {
                    //获取方法上的url映射地址
                    methodUrl = finalBaseUrl + declaredAnnotation1.value().toString();
                    //springMVC 容器  {key:url请求地址, value: Controller对象}
                    urlBeans.put(finalBaseUrl,bean);
                    //springMVC 容器  {key:url请求地址, value: Controller对象里面的方法的名称}
                    urlMethods.put(methodUrl,method.getName());
                }
            });
        });


    }
}
