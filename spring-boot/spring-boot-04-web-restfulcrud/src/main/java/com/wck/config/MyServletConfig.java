package com.wck.config;

import com.wck.filter.Myfilter;
import com.wck.listener.MyListener;
import com.wck.servlet.MyServlet;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

/**
 * @author 御香烤翅
 * @create 2020-03-15 16:50
 */

@Configuration
public class MyServletConfig {

    @Bean  //一定要将这个定制器加入到容器中
    public EmbeddedServletContainerCustomizer embeddedServletContainerCustomizer(){
        return new EmbeddedServletContainerCustomizer() {

            //定制嵌入式的Servlet容器相关的规则
            @Override
            public void customize(ConfigurableEmbeddedServletContainer container) {
                container.setPort(8083);
            }
        };
    }


    //注册一个servlet
    @Bean
    public ServletRegistrationBean myservlet(){
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new MyServlet(),"/myservlet");
        return servletRegistrationBean;
    }


    //注册一个filter
    @Bean
    public FilterRegistrationBean myfilter(){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new Myfilter());
        filterRegistrationBean.setUrlPatterns(Arrays.asList("/hello","/myservlet"));
        return filterRegistrationBean;
    }

    @Bean
    public ServletListenerRegistrationBean mylisterner(){
        ServletListenerRegistrationBean servletListenerRegistrationBean = new ServletListenerRegistrationBean(new MyListener());
        return servletListenerRegistrationBean;
    }

}
