package com.write.bservice;

import com.write.bservice.annotation.BrianResource;
import com.write.bservice.annotation.BrianService;
import com.write.bservice.annotation.utils.ClassUtil;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 *自定义注解版本的 IOC容器
 */
public class BrianAnnotationConfigApplicationContext {

    //扫描的包
    private  String packageName;

    private ConcurrentHashMap<String,Object> beans = null;

    public BrianAnnotationConfigApplicationContext(String packageName) throws Exception {
        beans = new ConcurrentHashMap<>();
        this.packageName = packageName;
        initBeans();
        initFields();
    }

    public Object getBean(String beanId) throws Exception {
        if(beanId == null || beanId.isEmpty()){
            throw new Exception("beanId不能为空");
        }
        String id = ClassUtil.toLowerCaseFirstOne(beanId);
        return beans.get(id);
    }

    /**
     * 初始化对象
     * 1.使用java反射机制扫描包并获取改包下面所有的类
     * 2.判断类上是否存在注入bean的注解
     * 3.使用java反射机制进行初始化
     */
   public void  initBeans() throws Exception {
    //1.使用java反射机制扫描包并获取改包下面所有的类
    List<Class<?>> classes = ClassUtil.getClasses(packageName);
    //2.判断类上是否存在注入bean的注解
       ConcurrentHashMap<String, Object> exitAnnotation = findClassExitAnnotation(classes);
       if(exitAnnotation == null || exitAnnotation.isEmpty()){
           throw  new Exception("扫描的包下面没有任何类加上@BrianService注解");
       }
   }

   public ConcurrentHashMap<String,Object> findClassExitAnnotation(List<Class<?>> classes){
    classes.stream().forEach(bean -> {
        BrianService annotation = bean.getAnnotation(BrianService.class);
        if(annotation != null){
            //获取当前类名
            String className = bean.getSimpleName();
            //存放beanid (类名首字母小写)
            String beanId = ClassUtil.toLowerCaseFirstOne(className);
            try {
                //3.使用java反射机制进行初始化
                beans.put(beanId, bean.newInstance());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    });
    return beans;
   }

    /**
     * 依赖注入注解原理
     * 1.使用反射机制，获取当前类的所有属性
     * 2.判断当前类的属性上是否有加注解
     * 3.默认使用属性名称去IOC容器查找对应的bean对象
     */

   public void attrAssign(Object object){
        //1.使用反射机制，获取当前类的所有属性
       Class<? extends Object> aClass1 = object.getClass();
       Field[] fields = aClass1.getDeclaredFields();
       //2.判断当前类的属性上是否有加注解
       Arrays.stream(fields).forEach(field -> {
           BrianResource brianResource = field.getAnnotation(BrianResource.class);
           if(brianResource != null){
               //获取属性名称
               String fieldName = field.getName();
               Object obj = beans.get(fieldName);
               if(obj != null){
                   //3.默认使用属性名称去IOC容器查找对应的bean对象
                   //允许访问私有属性
                   field.setAccessible(true);
                   try {
                       //参数1当前对象 ，参数2当前属性的bean对象
                       field.set(object,obj);
                   } catch (IllegalAccessException e) {
                       e.printStackTrace();
                   }
               }
           }
       });
   }

   //初始化属性
   public void initFields(){
        beans.entrySet().stream().forEach(value -> {
            Object v = value.getValue();
            attrAssign(v);
        });
   }
}
