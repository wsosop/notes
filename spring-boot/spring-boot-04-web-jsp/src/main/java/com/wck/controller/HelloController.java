package com.wck.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 御香烤翅
 * @create 2020-03-15 19:52
 */

@Controller
public class HelloController {


    @RequestMapping("/success")
    public String hello(Model model){

        System.out.println("hello...");
        model.addAttribute("name","wck");
        return "success";
    }

}
