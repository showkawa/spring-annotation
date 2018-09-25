package com.brian.async;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.UUID;

/**
 * DeferredResult异步调用
 *
 */
@Controller
public class DeferredResult02 {

    @RequestMapping("/create")
    public DeferredResult<Object> createOrder() {
        DeferredResult<Object> deferredResult =
                new DeferredResult<Object>((long)15000,"create order fail");
        //存放到模拟的队列中
        ResultQueue.add(deferredResult);
        return deferredResult;
    }


    @RequestMapping("/create/action")
    public String createAction() {
        DeferredResult<Object> deferredResult = ResultQueue.get();
        String order = UUID.randomUUID().toString();
        deferredResult.setResult(order);
        return " create order id: " + order;
    }

}
