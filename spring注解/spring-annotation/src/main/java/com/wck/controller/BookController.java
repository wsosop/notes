package com.wck.controller;

import com.wck.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @author 御香烤翅
 * @create 2020-03-09 19:49
 */
@Controller
public class BookController {

    @Autowired
    private BookService bookService;

}
