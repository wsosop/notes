package com.wck.spring.framework.context.support;

import com.wck.spring.framework.bean.BeanDefinition;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * 用于对配置文件，进行 查找、读取、解析
 * @author 御香烤翅
 * @create 2020-02-16 19:41
 */
public class BeanDefinitionReader {

    //用于存放配置文件中的 key value
    private Properties config=new Properties();

    //扫描的路径配置名称
    private final String SCAN_PACKAGE="scanPackage";

    //存储扫描路径下所有的类
    private List<String> registyBeanClasses=new ArrayList<String>();

    public BeanDefinitionReader(String [] configLocations){
        //1.定位
        //location 返回的是：classpath:application.properties
        //在spring中通过的是 reader 来查找和定位的
        InputStream is=this.getClass().getClassLoader().getResourceAsStream(configLocations[0].replace("classpath:",""));
        try {
            config.load(is);
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

        //2.扫描
        doScanner(config.getProperty(SCAN_PACKAGE));

    }


    //每注册一个className,就返回一个BeanDefinition
    //这个 BeanDefinition 是自己包装的,BeanDefinition自己的理解就是配置中配置的bean
    //目的只是为了对配置信息的一个包装
    public BeanDefinition registerBean(String className){
        if(registyBeanClasses.contains(className)){
            BeanDefinition beanDefinition=new BeanDefinition();
            beanDefinition.setBeanClassName(className);
            beanDefinition.setFactoryBeanName(lowerFirstCase(className.substring(className.lastIndexOf(".") + 1)));
            //beanDefinition.setLazyInit(false);//默认是单例，不使用懒加载
            return beanDefinition;
        }
        return null;
    }


    //返回所有的类,类的格式为全路径 ： 如 com.wck.spring.demo.action.MyAction
    public List<String> loadBeanDefinitions(){
        return this.registyBeanClasses;
    }

    //遍历指定 packageName包下的所有的 类
    //递归扫描所有的相关联的class，并且保存到一个List中
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
                registyBeanClasses.add(packageName+"."+file.getName().replace(".class",""));
            }

        }
    }


    //返回配置文件中的，key value
    public Properties getConfig(){
        return this.config;
    }


    //首字母小写
    private String lowerFirstCase(String str){
        char [] chars = str.toCharArray();
        chars[0] += 32;
        return String.valueOf(chars);
    }

}
