package com.brian.controller;

import com.brian.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

@Controller
public class BookController {

    @Autowired
    //@Qualifier("bookServiceImpl")
    private BookService bookService;

    private void printBook() {
        bookService.printBook();
    }

}
