package com.write.bservice.annotation.service;


import com.write.bservice.annotation.BrianResource;
import com.write.bservice.annotation.BrianService;

@BrianService
public class BaseService {


    @BrianResource
    private  OtherService otherService;

    public BaseService() {
        System.out.println("------ BaseService init-------");
    }

    public void add(){
        otherService.add();
    }
}
