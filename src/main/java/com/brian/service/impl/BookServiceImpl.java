package com.brian.service.impl;

import com.brian.service.BookService;
import org.springframework.stereotype.Service;

/**
 * @program: com.annotation
 * @author: Brian Huang
 * @create: 2019-07-03 14
 **/
@Service
public class BookServiceImpl implements BookService {
    @Override
    public void printBook() {
        System.out.println("---BookServiceImpl---: spring5源码分析");
    }
}
