package com.brian.async;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.concurrent.Callable;

/**
 * SpringMVC异步调用 Callable =>执行过程
 * 1.控制器返回Callable
 * 2.Spring异步调用，将Callable提交到TaskExecutor使用一个隔离的线程进行执行
 * 3.DispatcherServlet和所有的Filter退出web容器的线程，而response保持打开的状态
 * 4.Callable返回结果，SpringMVC将请求重新派发给容器，并且恢复之前的处理。
 * 5.根据Callable返回的结果。SpringMVC继续进行试图渲染流程等等等等。
 */
@Controller
public class Callable01 {

    @ResponseBody
    @RequestMapping("/async01")
    public Callable<String> async01() {

        System.out.println("主线程start...." + Thread.currentThread()+"-"+System.currentTimeMillis());
        Callable<String> callable = new Callable<String>() {

            public String call() throws Exception {
                System.out.println("callable线程start...." + Thread.currentThread() + "-" + System.currentTimeMillis());
                Thread.sleep(3000);
                System.out.println("callable线程end...." + Thread.currentThread() + "-" + System.currentTimeMillis());
                return "ok";
            }
        };
        System.out.println("主线程end...." + Thread.currentThread()+"-"+System.currentTimeMillis());
        return callable;
    }

}
