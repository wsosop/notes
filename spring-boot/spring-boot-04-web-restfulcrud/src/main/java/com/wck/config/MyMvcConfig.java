package com.wck.config;

import com.wck.component.LoginHandlerInterceptor;
import com.wck.component.MyErrorAttributes;
import com.wck.component.MyLocalResolver;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author 御香烤翅
 * @create 2020-03-14 15:12
 */

/**
 * 扩展配置，用于扩展springMVC的配置
 */

@Configuration
public class MyMvcConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //使用/wck路径，转向到 success页面
        //registry.addRedirectViewController("/wck","success");
        registry.addViewController("/wck").setViewName("success");
//        registry.addViewController("/").setViewName("login");
//        registry.addViewController("index.html").setViewName("login");
    }


    //使首页定向到index页面
    @Bean
    public WebMvcConfigurerAdapter webMvcConfigurerAdapter(){
        WebMvcConfigurerAdapter adapter = new WebMvcConfigurerAdapter() {

            //添加视图控制
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                registry.addViewController("/").setViewName("login");
                registry.addViewController("/index.html").setViewName("login");
                registry.addViewController("/main.html").setViewName("dashboard");
            }

            //注册拦截器
            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                //静态资源： *.css *.js
                //springboot 已经做好了 静态资源映射

//                registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**")
//                        .excludePathPatterns("/index.html","/","/user/login");
            }
        };
        return adapter;
    }

    //添加地区解析
    @Bean
    public LocaleResolver localeResolver(){
        return new MyLocalResolver();
    }


    //添加错误自定义的输出信息
    @Bean
    public MyErrorAttributes myErrorAttributes(){
        return new MyErrorAttributes();
    }

}
