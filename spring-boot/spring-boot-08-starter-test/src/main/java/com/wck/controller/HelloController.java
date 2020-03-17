package com.wck.controller;

import com.wck.starter.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author 御香烤翅
 * @create 2020-03-17 23:46
 */
@Controller
public class HelloController {

    @Autowired
    HelloService helloService;


    @ResponseBody
    @GetMapping("/hello")
    public String hello(){
        return helloService.sayHello("nihao");
    }


}
