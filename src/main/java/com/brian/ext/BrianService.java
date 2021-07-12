package com.brian.ext;


import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class BrianService {

    @EventListener
    public void listen(ApplicationEvent evevt){
        System.out.println("@EventListener 监听到事件 => " + evevt);

    }
}
