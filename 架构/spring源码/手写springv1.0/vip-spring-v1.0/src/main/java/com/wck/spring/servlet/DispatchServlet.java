package com.wck.spring.servlet;


import com.wck.spring.annotation.Autowried;
import com.wck.spring.annotation.Controller;
import com.wck.spring.annotation.Service;
import com.wck.spring.demo.mvc.action.DemoAction;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 御香烤翅
 * @create 2020-02-07 8:12
 */
public class DispatchServlet extends HttpServlet {


    //用于存放配置文件中的 key value
    private Properties contextConfig=new Properties();

    //用于存放所有的类名
    private List<String> classNames=new ArrayList<>();

    //用于模拟ioc容器，存放对应的beanName和实例
    private Map<String,Object> beanMap=new ConcurrentHashMap<String,Object>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("doGet...");
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("doPost...");
        System.out.println("开始执行调用请求url的方法...");
    }


    @Override
    public void init(ServletConfig config) throws ServletException {
        System.out.println("init...");
        System.out.println("加载核心的流程...");

        //定位
        doLoadConfig(config.getInitParameter("contextConfigLocation"));

        //加载
        doScanner(contextConfig.getProperty("scanPackage"));
        //System.out.println("classNames:"+classNames);

        //注册
        doRegistry();
        System.out.println("beanMap:"+beanMap);

        //依赖注入
        //spring中是通过getBean方法才触发依赖注入的
        doAutowired();

        DemoAction demoAction= (DemoAction) beanMap.get("demoAction");
        demoAction.query(null,null,"wck");


        //如果是springMVC会多设计一个 handlerMapping
        //将ReuestMapping 中配置的url和一个Method对应关联上
        //以便于从浏览器中获取用户输入的url以后，能够找到具体执行的Method通过反射去调用
        initHandlerMapping();
    }

    //1.定位
    private void doLoadConfig(String location) {
        //location 返回的是：classpath:application.properties
        //在spring中通过的是 reader 来查找和定位的
        InputStream is=this.getClass().getClassLoader().getResourceAsStream(location.replace("classpath:",""));
        try {
            contextConfig.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(is !=null){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    //2.加载
    //遍历指定 packageName包下的所有的 类
    private void doScanner(String packageName) {
        //packageName：com.wck.spring.demo
        URL url=this.getClass().getClassLoader().getResource("/"+packageName.replaceAll("\\.","/"));
        System.out.println("url:"+url);//输出为：url:file:/D:/ideaProject/source/vip-spring-v1.0/target/classes/com/wck/spring/demo
        File classDir=new File(url.getFile());//获取url路径下的文件
        for (File file:classDir.listFiles() ) {
            System.out.println("file.getName():"+file.getName());//输出：file.getName():mvc
            if(file.isDirectory()){//如果是文件夹，则需要继续遍历
                doScanner(packageName+"."+file.getName());
            }else {//加入到classNames  list集合中去
                classNames.add(packageName+"."+file.getName().replace(".class",""));
            }

        }
    }

    //3.注册
    private void doRegistry() {
        //如果classNames中没有扫描到的类，则直接返回

        /**
         * classNames:[
         * com.wck.spring.demo.mvc.action.DemoAction,
         * com.wck.spring.demo.mvc.action.MyAction,
         * com.wck.spring.demo.mvc.service.IDemoService,
         * com.wck.spring.demo.mvc.service.impl.DemoService
         * ]
         */

        if(classNames.isEmpty()){
            return;
        }

        try {

            for (String className:classNames) {
                Class<?> clazz=Class.forName(className);
                //在spring中使用多个子方法来处理相应的注解

                //如果是 Controller 控制器的注解
                if(clazz.isAnnotationPresent(Controller.class)){
                    //System.out.println("SimpleName:"+clazz.getSimpleName());
                    //System.out.println("SimpleName2:"+lowerFirstCase(clazz.getSimpleName()));
                    String beanName=lowerFirstCase(clazz.getSimpleName());
                    //在spring中这里不会直接put,而是put的是 beanDefinition
                    beanMap.put(beanName, clazz.newInstance());

                }else if(clazz.isAnnotationPresent(Service.class)){

                    Service service=clazz.getAnnotation(Service.class);
                    //默认不写，是使用首字母小写的名称
                    //如果设置了名称，则优先使用设置的名称
                    //如果使用的是接口，使用接口的类型去自动注入

                    //在Spring中同样会分别调用不同的方法 autowriedByName autowritedByType
                    String beanName=service.value();
                    System.out.println("beanName:"+beanName);
                    if("".equals(beanName.trim())){
                        beanName=lowerFirstCase(clazz.getSimpleName());
                    }

                    Object instance=clazz.newInstance();
                    beanMap.put(beanName,instance);

                    Class<?>[] interfaces=clazz.getInterfaces();
                    System.out.println("interfaces[]:"+Arrays.asList(interfaces));
                    for(Class<?> i:interfaces){
                        System.out.println("走到了接口这里");
                        beanMap.put(i.getName(),instance);
                    }

                }else{
                    continue;
                }



            }
        } catch (Exception e) {
            e.printStackTrace();
        }



    }

    //4.依赖注入
    private void doAutowired() {
        System.out.println("依赖注入...");
        if(beanMap.isEmpty()){
            return;
        }

        /**
         * entry:demoAction=com.wck.spring.demo.mvc.action.DemoAction@476ee5b3
         * entry:demoService=com.wck.spring.demo.mvc.service.impl.DemoService@59ed3e6c
         * entry:myAction=com.wck.spring.demo.mvc.action.MyAction@7cd4a4d7
         * entry:com.wck.spring.demo.mvc.service.IDemoService=com.wck.spring.demo.mvc.service.impl.DemoService@59ed3e6c
         */
        for(Map.Entry<String,Object> entry:beanMap.entrySet()){
            System.out.println("entry:"+entry);
            //获取类内部的 字段
            Field[] fields=entry.getValue().getClass().getDeclaredFields();
            for(Field field:fields){
                System.out.println("全部field:"+field);

                //不是自动注入的注解
                if(!field.isAnnotationPresent(Autowried.class)){
                    continue;
                }

                Autowried autowried= field.getAnnotation(Autowried.class);

                String beanName=autowried.value().trim();
                System.out.println("beanName:"+beanName);

                if("".equals(beanName.trim())){

                    System.out.println("field.getName()："+field.getName());//field.getName()：demoService
                    System.out.println("field.getType().getName()："+field.getType().getName());//field.getType().getName()：com.wck.spring.demo.mvc.service.IDemoService

                    beanName=field.getType().getName();
                }

                field.setAccessible(true);

                try {
                    //把当前的类中的 注入的属性值设置为 ioc 容器中的 value对象
                    field.set(entry.getValue(),beanMap.get(beanName));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }


            }




        }

    }


    //springMVC 中处理 url 的 handlerMapping
    private void initHandlerMapping() {
    }
    //首字母转换为小写
    private String lowerFirstCase(String s){
        char[] chars=s.toCharArray();
        chars[0]+=32;
        return String.valueOf(chars);
    }


}
