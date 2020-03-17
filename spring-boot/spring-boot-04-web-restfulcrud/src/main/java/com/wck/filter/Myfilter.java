package com.wck.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author 御香烤翅
 * @create 2020-03-15 16:58
 */
public class Myfilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("Myfilter...doFilter...");
        chain.doFilter(request,response);
    }

    @Override
    public void destroy() {

    }
}
