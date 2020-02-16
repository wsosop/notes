package com.wck.spring.framework.webmvc.servlet;

import com.wck.spring.framework.context.ApplicationContext;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 御香烤翅
 * @create 2020-02-16 19:21
 */
public class DispatchServlet extends HttpServlet {


    private final String LOCATION="contextConfigLocation";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("doGet...");
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("doPost...");
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        System.out.println("init...");
        ApplicationContext applicationContext=new ApplicationContext(config.getInitParameter(LOCATION));
    }
}
