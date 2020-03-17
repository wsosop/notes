package com.wck.component;

/**
 * @author 御香烤翅
 * @create 2020-03-14 20:07
 */


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
 * 通过链接来动态改变语言
 */
public class MyLocalResolver implements LocaleResolver {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public Locale resolveLocale(HttpServletRequest request) {

        String l = request.getParameter("l");
//        logger.info("获取到的多语言参数为：{}",l);
        Locale locale=Locale.getDefault();

        if(!StringUtils.isEmpty(l)){
            String[] split = l.split("_");
            locale=new Locale(split[0],split[1]);

        }
        return locale;
    }

    @Override
    public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {

    }
}
