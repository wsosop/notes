package com.wck.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author 御香烤翅
 * @create 2020-03-08 23:37
 */
@Controller
public class HelloWordController {

    @ResponseBody
    @RequestMapping("/hello")
    public String hello(){
        return "Hello Word !!!";
    }

}
