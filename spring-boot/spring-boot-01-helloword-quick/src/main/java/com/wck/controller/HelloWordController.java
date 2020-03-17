package com.wck.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 御香烤翅
 * @create 2020-03-08 23:37
 */
//@Controller
//@ResponseBody//表示所有的方法都加这个注解
@RestController
public class HelloWordController {

    @RequestMapping("/hello")
    public String hello(){
        return "Hello Word  quick!!!";
    }

}
