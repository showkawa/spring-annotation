import java.util.concurrent.ConcurrentHashMap;

/**
 * 手写springMVC原理分析
 * 1.创建一个前端控制器  BrianDispatcherServlet 拦截所有请求   （基于servlet实现）
 * 2.初始化操作 重写servlet init()方法
 * ####2.1 扫描包，将包下范围所有的类，注入到springMVC容器里面去
 * ####2.2 将URL映射和方法关联
 * #######2.2.1 判断类上是否有注解，通过java反射机循环遍历方法，判断方法上是否有注解，进行封装URL和方法对应
 * 3.处理请求，重写servlet的doGet()或者doPost()方法
 * ####3.1 获取请求url, 去urlBeans获取实例对象，成功湖区实例后，调用urlMethods获取方法名称，使用java反射机制执行方法
 *
 */
public class IOC_TEST_MVC {

    //springMVC 容器  {key:类名Id, value:对象}
    private ConcurrentHashMap<String,Object> springmvcBeans = new ConcurrentHashMap<>();
    //springMVC 容器  {key:url请求地址, value: Controller对象}
    private ConcurrentHashMap<String,Object> urlBeans = new ConcurrentHashMap<>();
    //springMVC 容器  {key:url请求地址, value: Controller对象里面的方法}
    private ConcurrentHashMap<String,Object> urlMethods = new ConcurrentHashMap<>();



}
