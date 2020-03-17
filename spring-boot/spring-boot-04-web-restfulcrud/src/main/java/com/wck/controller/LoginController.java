package com.wck.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @author 御香烤翅
 * @create 2020-03-14 20:27
 */
@Controller
public class LoginController {

    Logger logger = LoggerFactory.getLogger(getClass());

    @PostMapping("/user/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Map<String,Object> map,
                        HttpSession session){
        logger.info("获取到的请求用户名：{}，密码：{}",username,password);
//        if(!StringUtils.isEmpty(username) && "123456".equals(password)){
        if(!StringUtils.isEmpty(username)){//只验证用户名
            session.setAttribute("loginUser",username);
            return "redirect:/main.html";
        }else{
            map.put("msg","用户名密码错误");
            return "login";

        }



    }


}
