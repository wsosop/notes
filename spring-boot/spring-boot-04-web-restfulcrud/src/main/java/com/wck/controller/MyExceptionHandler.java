package com.wck.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 御香烤翅
 * @create 2020-03-15 12:47
 */
@ControllerAdvice
public class MyExceptionHandler
{

    //1.没有自适应，浏览器和客户端返回的都是json
/*    @ResponseBody
    @ExceptionHandler(Exception.class)
    public Map<String,Object>  handleException(Exception e){
        Map<String,Object> map=new HashMap<>();
        map.put("code","123456");
        map.put("message",e.getMessage());
        map.put("code","123456");

        return map;

    }*/

    //这边用于返回对象的状态码
    @ExceptionHandler(Exception.class)
    public String handleException(Exception e, HttpServletRequest request, HttpServletResponse response){
        Map<String,Object> map = new HashMap<>();
        //传入我们自己的错误状态码  4xx 5xx，否则就不会进入定制错误页面的解析流程
        /**
         * Integer statusCode = (Integer) request
         .getAttribute("javax.servlet.error.status_code");
         */
        //System.out.println("handleException...");
//        String attribute = (String) request.getAttribute("javax.servlet.error.status_code");
//
//        System.out.println("请求到的错误码："+attribute);


        request.setAttribute("javax.servlet.error.status_code",500);
        map.put("code","user.notexist");
        map.put("message","用户出错啦");
        request.setAttribute("ext",map);
        //转发到/error
        return "forward:/error";
    }


}
