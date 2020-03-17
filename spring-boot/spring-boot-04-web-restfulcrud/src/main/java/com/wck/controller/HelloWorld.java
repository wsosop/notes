package com.wck.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.Map;

/**
 * @author 御香烤翅
 * @create 2020-03-14 10:37
 */
@Controller
public class HelloWorld {

    //访问首页的第一种办法
//    @RequestMapping({"/","index.html"})
//    public String index(){
//        return "login";
//    }
    @ResponseBody
    @RequestMapping("/hello")
    public String hello(@RequestParam("aaa") String aaa) throws Exception {
        System.out.println("请求到hello...");
        if(aaa.equals("123")){
            System.out.println("123参数进来了...");
           throw new Exception("访问出错");
        }
        return "hello web";
    }

    //返回页面
    @RequestMapping("/success")
    public String success(Map<String,Object> map){
        map.put("hello","<h1>你好</h1>");
        map.put("users",Arrays.asList("zhangsan","lisi","wangwu"));
        return "success";
    }
}
