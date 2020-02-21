package com.wck.spring.framework.webmvc;

import java.lang.reflect.Method;
import java.util.regex.Pattern;

/**
 * @author 御香烤翅
 * @create 2020-02-19 14:27
 */
public class HandlerMapping {

    //用于保存控制器
    private Object controller;
    //用于保存方法
    private Method method;
    //用于保存对应的url匹配
    private Pattern pattern;

    public HandlerMapping( Pattern pattern,Object controller, Method method) {
        this.controller = controller;
        this.method = method;
        this.pattern = pattern;
        System.out.println("pattern.toString():"+pattern.toString());
    }

    public Object getController() {
        return controller;
    }

    public void setController(Object controller) {
        this.controller = controller;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public Pattern getPattern() {
        return pattern;
    }

    public void setPattern(Pattern pattern) {
        this.pattern = pattern;
    }
}
