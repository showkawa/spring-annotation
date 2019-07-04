package com.brian.service.impl;

import com.brian.service.BookService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

/**
 * @program: com.annotation
 * @author: Brian Huang
 * @create: 2019-07-03 14
 **/
@Service
@Primary
public class OtherBookServiceImpl implements BookService {
    @Override
    public void printBook() {
        System.out.println("---OtherBookServiceImpl---: 天道酬勤，一步一个坑");
    }
}
