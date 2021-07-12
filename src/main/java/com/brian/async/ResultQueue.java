package com.brian.async;

import org.springframework.web.context.request.async.DeferredResult;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * 模拟一个队列
 */
public class ResultQueue {

    private static Queue<DeferredResult<Object>> queue =
            new ConcurrentLinkedDeque<DeferredResult<Object>>();

    public static void add(DeferredResult<Object> deferredResult){
        queue.add(deferredResult);
    }

    public static DeferredResult<Object> get(){
        return queue.poll();
    }
 }
