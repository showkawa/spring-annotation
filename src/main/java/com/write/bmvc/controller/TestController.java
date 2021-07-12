package com.write.bmvc.controller;


import com.write.bmvc.annotation.BrianController;
import com.write.bmvc.annotation.BrianRequestMapping;

@BrianController
@BrianRequestMapping("/brian")
public class TestController {


    @BrianRequestMapping("/test")
    public String test() {

        System.out.println("手写springMV框架....");
        return "BRIAN";
    }
}
